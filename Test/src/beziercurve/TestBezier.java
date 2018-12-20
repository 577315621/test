package beziercurve;

import java.util.ArrayList;
import java.util.List;

public class TestBezier {

	//// 长度函数反函数计算终止条件
	private final double STOP_VALUE = 0.00001;
	// FPS
	private final int CACHE_PRECISION = 60;

	private Point[] points = null;
	private int timeLen = 0;
	private List<Integer> cache = null;
	private int totalLength = 0;
	private double a = 0;
	private double b = 0;
	private double c = 0;

	/**
	 * 构造二次贝塞尔曲线
	 * 
	 * @param points
	 *            三个控制点
	 * @param timeLen
	 *            时间长度
	 * @param toCache
	 *            是否缓存
	 */
	public void constructor(Point[] points, int timeLen, boolean toCache) {
		this.points = points;
		this.timeLen = timeLen;
		if (toCache) {
			this.cache = new ArrayList<>();
		}
		if (points[0].x == points[1].x && points[1].x == points[2].x) {
			points[2].x = points[2].x + 1;
		}
		if (points[0].y == points[1].y && points[1].y == points[2].y) {
			points[2].y = points[2].y + 1;
		}
		double ax = points[0].x - 2 * points[1].x + points[2].x;
		double ay = points[0].y - 2 * points[1].y + points[2].y;
		double bx = 2 * points[1].x - 2 * points[0].x;
		double by = 2 * points[1].y - 2 * points[0].y;

		this.a = 4 * (ax * ax + ay * ay);
		this.b = 4 * (ax * bx + ay * by);
		this.c = bx * bx + by * by;
	}

	/**
	 * 获取时间长度
	 */
	public int getTimeLength() {
		return this.timeLen;
	}

	/**
	 * 返回【匀速运动】情况下指定时间点坐标
	 * 
	 * @param time
	 *            范围0~1
	 * @param fromCache
	 *            对应时间点的坐标
	 */
	public Point[] getPoints(double time, boolean fromCache) {
		long timeIndex;
		if (this.cache != null && fromCache) {
			timeIndex = Math.round(time * CACHE_PRECISION);
			Integer p02x = this.cache.get((int) (4 * timeIndex - 3));
			Integer p02y = this.cache.get((int) (4 * timeIndex - 2));
			Integer p01x = this.cache.get((int) (4 * timeIndex - 1));
			Integer p01y = this.cache.get((int) (4 * timeIndex));
			if (p02x != null) {
				return new Point[]{new Point(p02x, p02y),new Point(p01x, p01y)};
			}
		}
		time = time / this.timeLen;
        //如果按照线性增长,此时对应的曲线长度
        double length = time * this.totalLength;
        //根据L函数的反函数，求得l对应的t值
        time = this.invertLength(time, length);
		
        //根据贝塞尔曲线函数，求得取得此时的x,y坐标
        double x= (1 - time) * this.points[0].x + time * this.points[1].x;
        double y = (1 - time) * this.points[0].y + time * this.points[1].y;
        Point p01 =new Point(x, y);
        
        x = (1 - time) * this.points[1].x + time * this.points[2].x;
        y = (1 - time) * this.points[1].y + time * this.points[2].y;
        Point p11 =new Point(x, y);

        x = (1 - time) * p01.x + time * p11.x;
        y = (1 - time) * p01.y + time * p11.y;
        Point p02 =new Point(x, y);
		
		return new Point[]{p02,p01};
	}

	/**
	 * 速度
	 * 
	 * @param time
	 * @return
	 */
	private double speed(double time) {
		return Math.sqrt(this.a * time * time + this.b * time + this.c);
	}

	/**
	 * 长度函数 L(t) = Integrate[s[t], t] L(t_) = ((2*Sqrt[A]*(2*A*t*Sqrt[C + t*(B +
	 * A*t)] + B*(-Sqrt[C] + Sqrt[C + t*(B + A*t)])) + (B^2 - 4*A*C) (Log[B +
	 * 2*Sqrt[A]*Sqrt[C] ] - Log[B + 2*A*t + 2 Sqrt[A]*Sqrt[C + t*(B + A*t)] ]))
	 * / (8* A^(3/2)));
	 * 
	 * @param time
	 *            时间
	 */
	private double length(double time) {
		double temp1 = Math.sqrt(this.c + time * (this.b + this.a * time));
		double temp2 = (2 * this.a * time * temp1 + this.b * (temp1 - Math.sqrt(this.c)));
		double temp3 = Math.log(this.b + 2 * Math.sqrt(this.a) * Math.sqrt(this.c));
		double temp4 = Math.log(this.b + 2 * this.a * time + 2 * Math.sqrt(this.a) * temp1);
		double temp5 = 2 * Math.sqrt(this.a) * temp2;
		double temp6 = (this.b * this.b - 4 * this.a * this.c) * (temp3 - temp4);
		return (temp5 + temp6) / (8 * Math.pow(this.a, 1.5));
	}

	/**
	 * 长度函数反函数，使用牛顿切线法求解 X(n+1) = Xn - F(Xn)/F'(Xn)
	 * 
	 * @param time
	 *            时间
	 * @param length
	 *            长度
	 */
	private double invertLength(double time, double length) {
		double t1 = time;
		double t2;
		while (true) {
			t2 = t1 - (this.length(t1) - length) / this.speed(t1);
			if (Math.abs(t1 - t2) < STOP_VALUE)
				break;
			t1 = t2;
		}
		return t2;
	}

	class Point {
		public double x;
		public double y;
		public Point(double x,double y){
			this.x =x;
			this.y=y;
		}
	}
}

package tink;

import java.security.GeneralSecurityException;

import com.google.crypto.tink.KeyManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageLite;

public class MyAeadKeyManager implements KeyManager {

	@Override
	public boolean doesSupport(String typeUrl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getKeyType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrimitive(ByteString serializedKey) throws GeneralSecurityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrimitive(MessageLite key) throws GeneralSecurityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MessageLite newKey(ByteString serializedKeyFormat) throws GeneralSecurityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageLite newKey(MessageLite keyFormat) throws GeneralSecurityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyData newKeyData(ByteString serializedKeyFormat) throws GeneralSecurityException {
		// TODO Auto-generated method stub
		return null;
	}

}

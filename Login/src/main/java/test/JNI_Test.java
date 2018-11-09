package test;

public class JNI_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary("JNI_Test");
		JNI_Test t = new JNI_Test();
		t.Test();
	}
	public native void Test();
}

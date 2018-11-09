package tt.tools;

import java.util.UUID;

public class Uid {
	public static String getUid(){
		String uid=UUID.randomUUID().toString().trim().replace("-", "");
		return uid;
	}
}

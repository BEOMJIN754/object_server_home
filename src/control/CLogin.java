package control;

import model.DataAccessObject;
import model.MLogin;
import valueObject.VLogin;

public class CLogin {

	public boolean validateUser(VLogin vLogin) {
		boolean bResult = false;
		
		DataAccessObject dataAccessObject = new DataAccessObject();
		MLogin mLogin = (MLogin) dataAccessObject.getAModel("UserId", MLogin.class, vLogin.getUserId());
		if (mLogin != null) {
			//입력 비밀번호와 파일에서 읽어온 비밀번호가 맞으면 트루를 만든다. 아니면 펄스
			if (vLogin.getPassword().contentEquals(mLogin.getPassword())) {
				bResult = true;
			} else {
				// password mismatch
			}
		} else {
			// no userId
		}		
		return bResult;
	}
}

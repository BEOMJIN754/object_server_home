package model;

import java.util.Vector;

public class DaoDB implements Dao{
	
	public MModel getARow(String fileName, String key,Class<?> clazz) {
		
		return null;
	}

	public Vector<MModel> getRows(String fileName, Class<?> clazz) {
		Vector<MModel> mModels = new Vector<MModel>();
		
		return mModels;
	}

	public void setRows(String fileName, Vector<MModel> mModels) {
			
	}

	@Override
	public void setARows(String fileName, String key, Vector<MModel> mModels) {
		// TODO Auto-generated method stub
		
	}
	
	
}

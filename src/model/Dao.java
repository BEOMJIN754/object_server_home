package model;

import java.util.Vector;

public interface Dao {

	public MModel getARow(String fileName,String key, Class<?> clazz );
	public Vector<MModel> getRows(String fileName, Class<?> clazz) ;
	
	public void setARows(String fileName, String key,Vector<MModel> mModels) ;
	public void setRows(String fileName, Vector<MModel> mModels) ;
	}

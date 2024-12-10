package model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Vector;

public class DaoDB implements Dao {

    private final String dbUrl = "jdbc:mysql://localhost:3306/objectlms";
    private final String dbUser = "root";
    private final String dbPassword = "0304";

    // Utility function to map a ResultSet row to a model object
    private String read(MModel model, ResultSet resultSet) throws SQLException {
        String key = null;
        try {
            Field[] fields = model.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = resultSet.getObject(field.getName());
                field.set(model, value);
                if (key == null) {
                    key = (String) value; // Assume the first field is the key
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return key;
    }

    // Utility function to save a model object into a PreparedStatement
    private void save(MModel model, PreparedStatement preparedStatement) throws SQLException {
        try {
            Field[] fields = model.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                preparedStatement.setObject(i + 1, fields[i].get(model));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // Retrieve a single row based on the key
    public MModel getARow(String tableName, String key, Class<?> clazz) {
        String query = "SELECT * FROM " + tableName + " WHERE userid = ?";
        System.out.println(tableName);
        System.out.println(key);
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
        	System.out.println("success");
            preparedStatement.setString(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Constructor<?> constructor = clazz.getConstructor();
                MModel mModel = (MModel) constructor.newInstance();
                this.read(mModel, resultSet);
                return mModel;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve multiple rows
    public Vector<MModel> getRows(String tableName, Class<?> clazz) {
        Vector<MModel> mModels = new Vector<>();
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Constructor<?> constructor = clazz.getConstructor();
                MModel mModel = (MModel) constructor.newInstance();
                this.read(mModel, resultSet);
                mModels.add(mModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mModels;
    }

    // Save multiple rows to the database
    public void setRows(String tableName, Vector<MModel> mModels) {
        if (mModels.isEmpty()) return;

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            Field[] fields = mModels.get(0).getClass().getDeclaredFields();
            StringBuilder queryBuilder = new StringBuilder("INSERT INTO " + tableName + " (");

            for (Field field : fields) {
                queryBuilder.append(field.getName()).append(",");
            }
            queryBuilder.setLength(queryBuilder.length() - 1); // Remove trailing comma
            queryBuilder.append(") VALUES (");
            for (int i = 0; i < fields.length; i++) {
                queryBuilder.append("?,");
            }
            queryBuilder.setLength(queryBuilder.length() - 1); // Remove trailing comma
            queryBuilder.append(")");

            String query = queryBuilder.toString();

            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                for (MModel mModel : mModels) {
                    this.save(mModel, preparedStatement);
                    preparedStatement.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setARows(String fileName, String key, Vector<MModel> mModels) {
        // Not implemented based on the provided `DaoFile` code
    }
}

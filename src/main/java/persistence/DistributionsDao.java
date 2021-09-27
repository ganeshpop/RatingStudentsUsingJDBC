package persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DistributionsDao {

    public int getCategoryWeightage(String category) throws SQLException, IOException, ClassNotFoundException {
        Connection connection = MySQLConnectionHelper.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT WEIGHT FROM distributions WHERE ASSIGNMENT_CATEGORY = ?;");
        preparedStatement.setString(1, category);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return resultSet.getInt("WEIGHT");
        }
        return 0;
    }
}

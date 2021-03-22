public class TeamProjectTestDriver{
    public static void main(String[] args){
        String username = "jpeaceAdmin ";
        String password = "Admin123";
        String firstName = "Courtney";
        String lastName = "Perreault";

        System.out.println("insert into MemberAccount values('" + username + "', sha1('" + password + "'), '" 
						   + firstName + "', '" + lastName + "');");
        String sqlQuery = "select * from AdminAccount where username = '" + username +
						  "' and password = sha1('" + password + "');";
                          
        System.out.println(sqlQuery);

        DataManager dm = new DataManager();

        dm.addMemberAccount("courtneyp0726", "password", "Courtney", "Perreault");

                    
    }
}
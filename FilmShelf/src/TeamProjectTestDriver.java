public class TeamProjectTestDriver{
    public static void main(String[] args){
        String username = "jpeaceAdmin ";
        String password = "Admin123";
        String firstName = "Courtney";
        String lastName = "Perreault";
        
        /*
        System.out.println("insert into MemberAccount values('" + username + "', sha1('" + password + "'), '" 
						   + firstName + "', '" + lastName + "');");
        String sqlQuery = "select * from AdminAccount where username = '" + username +
						  "' and password = sha1('" + password + "');";
                          
        System.out.println(sqlQuery);
        */

        DataManager dm = new DataManager();

        //dm.addMemberAccount("courtneyp0726", "password", "Courtney", "Perreault");
        
        // testing toString methods (MovieObject toString is performed in member.toString() call)
        MemberObject member = dm.getMember("joboy", "pword1");
        System.out.println(member.toString());
        
        AdminObject admin = dm.getAdmin("joboyAdmin", "HeroPizza100");
        System.out.println(admin.toString());

        RatingObject rating = new RatingObject(4,"joboy",1);
        System.out.println(rating);
        
        ReviewObject review = new ReviewObject(1, "joboy", "Great flick", 1);
        System.out.println(review);
    }
}
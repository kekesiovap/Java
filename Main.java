import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args){
        SimpleDateFormat dateformat3 = new SimpleDateFormat("yyyy-mm-dd");
        Date date1 = null;
        try {
            date1 = dateformat3.parse("1995-06-15");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Person pers=new Person("Lila", "Mila", "955515/1547",  date1);
        Database database=new Database();

        Person p=database.selectPerson("Daco");
        System.out.println(p.getName()+ " "+p.getSurename());

        Person p2=database.selectPersonByPid("951212/1547");
        System.out.println(p2.getName()+ " "+p2.getSurename());

        List<Person> persons=database.getAllMen();
        for (Person person : persons){
            System.out.println(person.getName()+" "+person.getSurename());
        }
    }
}


package govisethalotteryresults;

/**
 *
 * @author KRISHAN
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.Statement;
//import java.io.IOException;

public class GovisethaLotteryResults {

    public static void main(String[] args) {
        try{
            String url = "https://www.nlb.lk/results/govisetha";
            Document doc = Jsoup.connect(url).get();
            Element table = doc.select("table").get(0);
            Element tbody = doc.select("tbody").get(0);
            Elements tr = doc.select("tr");
            Statement s = Database.getStatement();
            for(Element row:tr){
                
                Elements data = doc.select("td");
                
                for(Element value:data){
                    System.out.print(value.text());
                    System.out.println("");                   
                }
                String draw_date = data.get(0).text();
                String results = data.get(1).text();
                
                String query = ("INSERT INTO `lottery_results` (`draw_date`, `results`) VALUES ( '"+draw_date+"', '"+results+"')");
                s.executeUpdate(query);
                
            }
        }
        
        catch(Exception e){
            //e.printStackTrace();
        }
        
        
    }
    
}

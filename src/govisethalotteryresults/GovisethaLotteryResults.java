
//package govisethalotteryresults;
//
///**
// *
// * @author KRISHAN
// */
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import java.sql.Statement;
////import java.io.IOException;
//
//public class GovisethaLotteryResults {
//
//    public static void main(String[] args) {
//        try{
//            String url = "https://www.nlb.lk/results/govisetha";
//            Document doc = Jsoup.connect(url).get();
//            Element table = doc.select("table").get(0);
//            Element tbody = doc.select("tbody").get(0);
//            Elements rows = doc.select("tr");
//            
//            Statement s = Database.getStatement();
//            for(Element row:rows){
//                
//                Elements data = doc.select("td");
//                String[] myArray = new String[3];
//                int i = 0;
//                for(Element value:data){
//                    System.out.print(value.text());
//                    System.out.println("");
//                    
//                    myArray[i] = value.text();
//                    i++;
//                }
//               
//                String query = ("INSERT INTO `lottery_results` (`draw_date`, `results`) VALUES ('"+myArray[0]+"', '"+myArray[1]+"')");
//                s.executeUpdate(query);
//                
//            }
//        }
//        
//        catch(Exception ex){
//            ex.printStackTrace();
//        }
//        
//        
//    }
//    
//}


package govisethalotteryresults;

import org.jsoup.Jsoup;
//import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.sql.Statement;

/**
 *
 * @author KRISHAN
 */
public class GovisethaLotteryResults {
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            String url = "https://www.nlb.lk/English/results/govisetha";
            Document doc = Jsoup.connect(url).get();
            Element table = doc.select("table").get(0);
            Elements rows = table.select("tr");
            Statement s = Database.getStatement();
            
            for(int i=1;i<rows.size()-1;i++){
                String drawNo;
                String drawDate;
                String[] WiningNos = new String[5];
                
                Element row = rows.get(i);
                Elements data = row.select("td");
                
                drawNo = data.get(0).getElementsByTag("b").text();
                System.out.println("Draw Number : "+drawNo);
                drawDate = data.get(0).text().substring(5);
                System.out.println("Draw Date : "+drawDate);
                
                Element result = data.get(1);
                Elements results = result.getElementsByTag("li");
                
                int j = 0;
                for(Element res:results){
                    WiningNos[j] = res.text();
                    System.out.print(WiningNos[j]+" ");
                    j++;                   
                }
                
                String pqr = ("INSERT INTO `result` (`drawno`, `drawdate`, `results`) VALUES ( '"+drawNo+"', '"+drawDate+"', '"+ WiningNos[0]+" "+WiningNos[1]+" "+WiningNos[2]+" "+WiningNos[3]+" "+WiningNos[4]+"')");
                s.executeUpdate(pqr);
                
                System.out.println();
                System.out.println();
                
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
            
            
            
        }
        
    }
    
}

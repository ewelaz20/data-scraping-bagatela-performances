import groovy.json.JsonOutput

@Grab('com.sparkjava:spark-core:2.3')
@Grab('org.jsoup:jsoup:1.10.2')
import static spark.Spark.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document

def getMonthYear(){
    monthYear = new Date()
    monthYear = monthYear.format("MM-yyyy")
    return monthYear
}


def addObject(date) {

    List<Data> result = []

    Document doc = Jsoup.connect("http://www.bagatela.pl/repertuar/karmelicka").get();
    def results = doc.select("ul.m-calendar__list li")
    def bagatelaLink = "https://www.bagatela.pl"

    results.eachWithIndex { row, index ->
        day = row.attr("data-day")
        if (day == date) {

            // prepare hour format
            hour = row.text().split(" ")[0]


            // prepare link format
            link = row.select("a[href]")
            link = link.attr("href")


            // prepare title format
            title = link.split("/")[2]
            title = title.replace("-", " ")

            // prepare scene fromat
            scene = row.select("a div span")
            scene = scene.text()

            d = getMonthYear()

            result.add(new Data(date+"-"+d, title, hour, scene, bagatelaLink + link))
        }

    }
    return result
}


externalStaticFileLocation("index.html")
get  '/spektakle/:data' , {req, res ->
    def lista = addObject(req.params(":data"))
    return JsonOutput.toJson(lista)
}



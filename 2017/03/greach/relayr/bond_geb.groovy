@Grapes([
    @Grab("org.gebish:geb-core:1.1.1"),
    @Grab("org.seleniumhq.selenium:selenium-htmlunit-driver:2.52.0"),
    @Grab("org.seleniumhq.selenium:selenium-support:2.52.0")
])
import geb.Browser
import groovy.json.JsonOutput

Browser.drive{
    go "http://www.twerponline.net/timewell/databases/bond.htm"     
              
    def films = $("a")
    .findAll { anchor -> anchor.@name }
    .collect(this.&movieFromAnchor)
     
    new File('/home/dev/bond.json').text = JsonOutput.prettyPrint(JsonOutput.toJson(films))
             
}

Map movieFromAnchor(def anchor) {
    def table = anchor.find('table')
    def title = table.find('b', 0).text()
    
    def first = table.find('tr', 1)
    def year  = first.find('td', 0).text()
    def directedBy  = first.find('td', 1).text() - 'directed by '
    def producedBy  = first.find('td', 2).text()   
     
    def second = table.find('tr', 2)
    def bond = second.find('td', 1).text()
    
    def third = table.find('tr', 3)
    def girls = third.find('td', 2).text() ?
                    [third.find('td', 1).children()*.text().findAll(), 
                     third.find('td', 2).text().split('\n') as List].transpose()
                     .collect { c, a -> [(c):a - 'played by '] }
                     : [:]
    
    def fourth = table.find('tr', 4)
    
    def villains = fourth.find('td', 2).text() ?
                    [fourth.find('td', 1).children()*.text().findAll(), 
                     fourth.find('td', 2).text().split('\n') as List].transpose()
                     .collect { c, a -> [(c):a - 'played by '] }
                     : [:]
    
    def fifth = table.find('tr', 5)
    def counterparts =  fifth.find('td', 2).text() ?
                    [fifth.find('td', 1).children()*.text().findAll(), 
                     fifth.find('td', 2).text().split('\n') as List].transpose()
                     .collect { c, a -> [(c):a - 'played by '] }
                     : [:]
    
    def sixth = table.find('tr', 6)
    def themeSong = sixth.find('td', 1).text()
    def performedBy = sixth.find('td', 2).text()
    
    def seventh = table.find('tr', 7)
    def gadgets = seventh.find('td', 1).children()*.text().findAll() + 
                  seventh.find('td', 2).children()*.text().findAll()
                  
    def eight = table.find('tr', 8)
    def vehicles = eight.find('td', 1).children()*.text().findAll() + 
                  eight.find('td', 2).children()*.text().findAll()
    
    return [
        title: title,
        year: year,
        directedBy: directedBy,
        producedBy: producedBy,
        bond: bond, // 2.1
        bondGirls: girls,
        villains: villains,
        counterparts: counterparts,
        themeSong: [title: themeSong, performedBy: performedBy],
        gadgetsAndWeapons: gadgets,
        vehicles: vehicles
    ]
}
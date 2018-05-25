DATA_FILE = 'mlb_players.csv' as File

def CONTESTANTS = DATA_FILE
    .readLines()
    .drop(1)
    .collect { it.split(',') }
    .findAll { it.size() >= 5 }
    .collect { [name: it[0], age: it[5] as Double] }

CONTESTANTS

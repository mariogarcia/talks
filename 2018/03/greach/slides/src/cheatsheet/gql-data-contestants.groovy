DATA_FILE = 'mlb_players.csv' as File

def CONTESTANTS = DATA_FILE
    .readLines()
    .collect { it.split(',').first().replaceAll('"','') }
    .grep()
    .collect { [name: it] }

CONTESTANTS

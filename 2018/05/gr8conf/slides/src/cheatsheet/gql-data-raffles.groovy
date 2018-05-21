CONTESTANTS = evaluate('gql-data-contestants.groovy' as File)

return [
    [id: 1, title: 'Greach T-shirt', noWinners: 4, contestants: CONTESTANTS],
    [id: 3, title: 'Trip to Bahamas', noWinners: 1, contestants: CONTESTANTS],
    [id: 4, title: 'Free Beer', noWinners: 10, contestants: CONTESTANTS]
]

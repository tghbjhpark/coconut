# coconut

## Private API

### Header
api-client-type=2   
Api-Sign   
1. endpointUrl
2. urlparameter
3. nonce
example) /info/balance;endpoint=%2Finfo%2Fbalance&currency=ALL;1618924395580
asHex(hmacSha512(str, secret))

Api-Nonce   
Api-Key   

### Body
endpoint=/info/balance, currency=ALL

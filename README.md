## Cykl życia aplikacji. Przełączanie między aktywnościami.

### Przeprowadzona analiza do zadania 6.
Uruchom aplikację i zbadaj, jakie metody cyklu życia są wywoływane przy wykonywaniu następujących czynności:
 - Po pierwszym uruchomieniu aplikacji
 ```
    On Start Method                   D  fired up
    On Resume Method                  D  fired up
 ```
 - Po wciśnięciu przycisku Cofnij
 ```
    On Puse Method                    D  fired up
    On Stop Method                    D  fired up
 ```
 - Po ponownym uruchomieniu aplikacji
 ```
    On Start Method                   D  fired up
    On Resume Method                  D  fired up
 ```
 - Po wciśnięciu przycisku ekranu głównego Home
 ```
    On Start Method                   D  fired up
    On Resume Method                  D  fired up
 ```
 - Po powrocie do aplikacji z listy ostatnio używanych aplikacji
 ```
    On Start Method                   D  fired up
    On Resume Method                  D  fired up
 ```

Wszystkie logi zapisane są w pliku [logs.txt](logs.txt).
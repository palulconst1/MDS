# Component name
### Description

This is a card game in which you can bet if your hand of cards is les than 21

### Classes - type and usage
1. ##### CardDeck
    ```
    Aceasta clasa creeaza pachetul de carti si contine metodele:
    getCard (pentru a selecta un card random din packet) 
    shuffleDeck (pentru a dat shuffle pachetului)
    
    ```
2. ##### CardHand
    ```
   Aceasta clasa este folosita pentru a tine mine mainile jucatorilor PlayerHand si DealerHand si contine metodele:
   addCard (adauga un obiect de tip Carte maini Jucatorului)
   sumHand (calculeaza suma cartilor dintr-o mana)
   afisHand (afiseaza cartile)
   lastElementId(returneaza ultimul id)
    ```  
3. ##### Card
    ```
    Clasa ce defineste obiectul carte de joc (symbol si numar)
    ```  
4. ##### BlackJack
    ```
    Exista comentarii detaliate pe cod, aceasta este o scurta prezentare
    Clasa jocului
    Pentru a incepe in joc nou se apeleaza :
    Blackjack alpha = new Blackjack(playerName,fonduriPlayer);
    
    Constructorul clasei imi creeaza fereastra de start unde se pune bet-ul ( se verifica de asemenea daca acesta este de tip int/valid <= fonduriPlayer)
    De asemenea, exista si un buton pentru a se afisa regulile jocului.
    
    
    
    ```  

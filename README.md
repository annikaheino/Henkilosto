
# Henkilöstöjärjestelmä
*Kyseessä on yrityksen henkilöstöjärjestelmä, jossa on listattuna yrityksen työntekijät yksiköittäin ja palkkatiedoilla. Järjestelmään lisätään henkilöitä uusien työntekijöiden aloittaessa, poistetaan henkilöitä heidän lähtiessä yrityksestä sekä muokataan henkilöihin liittyvää tietoa. Muokkaustarpeita voi esiintyä yksikköön, titteliin, nimeen tai palkkaan liittyen. Järjestelmän tietoja voi käyttää mm. organisaation raportoinnissa ja kustannuslaskennassa.*
## Käyttäjäroolit
Pääkäyttäjänä henkilöstöjärjestelmässä toimii HR-asiantuntija, jolla on kaikki käyttöoikeudet. Lähtökohtaisesti vain pääkäyttäjä lisää, muokkaa ja poistaa henkilöitä. Pääkäyttäjän lisäksi järjestelmään pääsee johto ja esimiehet. Tarkoituksena olisi, että johdolla on laajat raportointioikeudet. Esimiehet taas pääsevät raportoimaan oman yksikkönsä tietoja. Seuraavana tavoitteena on muokata käyttöoikeuksia niin, että esimiehet pääsevät muokkaamaan vain oman yksikkönsä tietoja, mutta lisäys- ja poisto-oikeudet pysyvät edelleen pääkäyttäjällä.
## Kuvaus ohjelmiston toiminnallisista vaatimuksista
1. Henkilöiden lisäys
1. Henkilön tietojen muokkaus
1. Henkilöiden poisto
1. Raportointi (yksilö, yksikkö, koko organisaatio)
## Projektisuunnitelma
Ensin luodaan tietokantasuunnitelma ja yksinkertainen tekstipohjainen käyttöliittymä sekä testataan henkilöiden lisäystä ja poistoa.
Seuraavaksi keskitytään palkanosien laskennan toimivuuteen ja testaukseen sekä tietojen muokkaamisen toimintaan. Käyttöliittymää viedään eteenpäin kohti graafista.
Graafisen käyttöliittymän kehitystä jatketaan ja keskitytään raportointiin sekä käyttäjäroolien toimivuuteen. Mikäli aikaa jää järjestelmän viimeistelyltä, pohditaan tietojen lukemista sisään massana esim. csv muodossa.
## Rajoituksien pohdinta
Työkalun yksinkertaistamiseksi pääkäyttäjärooli on melko kuormitettu, toisaalta se mahdollistaa sen, että vain yksi henkilö kerrallaan tekee muutoksia. Mikäli käyttäjäroolit saadaan asetettua niin, että vain henkilön esimies pystyy tekemään henkilön tietoihin muutoksia, ei päällekkäisiä muokkauksia pääse tapahtumaan. Tällöin hallinnon roolina olisi pelkästään henkilöiden lisäys ja poisto. Johdon rooliksi jäisi siis pelkästään tietojen katsominen raporteilta. Mikäli johto ei osaa tai halua ottaa raportteja, voi HR tehdä johdolle valmisraportteja.
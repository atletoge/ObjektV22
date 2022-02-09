import hashlib

dummy = True

def registrer():
    brukernavn = input("Tast inn ønsket brukernavn: ")
    passord = input("Tast inn ønsket passord: ")
    passord2 = input("Skriv inn passord igjen: ")

    if passord == passord2:
        pass_enc = passord2.encode()
        hash1 = hashlib.md5(pass_enc).hexdigest()


        f = open("pythonsucks.txt", "w+")
        f.write(brukernavn + "\n")
        f.write(hash1)
        f.close()
        print("Buker er nå opprettet! ")
        
    else:
        print("Passordet stemmer ikke overens! ")

def login():
    brukernavn = input("Tast inn ditt brukernavn: ")
    passord = input("Tast inn ditt passord: ")

    autentiser = passord.encode()
    hash1 = hashlib.md5(autentiser).hexdigest()

    with open("logins.txt", "r") as f:
            brukernavn1, passord2 = f.read().split("\n")
            f.close()
    
    if brukernavn == brukernavn1 and hash1 == passord2:
        print(" Du er nå logget inn")
    else:
        print("Feil i pålogging. ")


while dummy:
    print("Du må logge inn for å begynne å chatte! ")
    valg = int(input("Tast 1 for registrering, 2 for login og 3 for å avslutte: "))
    if valg == 1:
        registrer()
    if valg == 2:
        login()
    if valg == 3:
        break
    else:
        print("Ugyldig input! ")
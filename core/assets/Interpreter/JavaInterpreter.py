import sys
class Interprete(object):
    resultado = ""
    """ Objeto que representa al jugador del juego"""
    def __init__(self):
        super(Interprete, self).__init__()
        if len(sys.argv) > 1:
            self.codigo = sys.argv[1]
            if self.codigo != "":
                try:
                    exec(self.codigo) # Interpretar el argumento de sistema
                except Exception as e:
                    print("ERROR: " + str(e))
            else:
                print("BLANK")
        else:
            print("BLANK")

    def movefd(self):
        print("fd")

    def movebck(self, posiciones):
        print("bc")

    def jump(self):
        print("ju")

    def boxDispose(self, number):
        print("di" + number)

def main():
    interprete = Interprete()

if __name__ == '__main__':
    main()

#  JavaInterpreter.py
#  Autores: Custodio, R.; Mendoza, E; Brolo, G y Rosales, J.
#  Fecha: 15/09/2015
#  Descripcion: Interprete que recibe argumentos del sistema provenientes
#  de java y luego los imprime para asi obtener el buffer y ejecutarlo en java

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

    def movebck(self):
        print("bc")

    def jump(self):
        print("ju")

    def boxDispose(self, number):
        print("di" + number)

    def jumpfd(self):
        print("jfd")

    def jumpbck(self):
        print("jbc")

    def exitmenu(self):
        print("exm")

    def cryforhelp(self):
        print("help")

    def musicOn(self):
        print("musicOn")

    def musicOff(self):
        print("musicOff")

def main():
    interprete = Interprete()

if __name__ == '__main__':
    main()

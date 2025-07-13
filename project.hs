-- My Start to the calculator
-- import Data.List
import Data.Char -- for isAlphaNum, isDigit, isSpace

data Chemical = Mul Ele Integer | Sing Ele | Sub Integer
  deriving (Show, Eq)

data Ele = Carbon | Hydrogen | Oxygen | Helium | Lithium | Beryllium | Boron | Nitrogen | Fluorine | Neon | Sodium | Magnesium | Aluminium | Silicon | Phosphorus | Sulfur | Chlorine | Argon | Potassium | Calcium | Scandium | Titanium | Vanadium | Chromium | Manganese | Iron | Cobalt | Nickel | Copper | Zinc | Gallium | Germanium | Arsenic | Selenium | Bromine | Krypton | Rubidium | Strontium | Yttrium | Zirconium | Niobium | Molybdenum | Technetium | Ruthenium | Rhodium | Palladium | Silver | Cadmium | Indium | Tin | Antimony | Tellurium | Iodine | Xenon | Caesium | Barium | Lanthanum | Hafnium | Tantalum | Tungsten | Rhenium | Osmium | Iridium | Platinum | Gold | Mercury | Lead | Bismuth | Polonium | Astatine | Radon | Francium | Radium | Rutherfordium | Dubnium | Seaborgium | Bohrium | Hassium | Meitnerium | Darmstadtium | Roentgenium | Copernicium | Nihonium | Flerovium | Moscovium | Livermorium | Tennessine | Oganesson | Cerium | Praseodymium | Neodymium | Promethium | Actinium | Samarium | Europium | Gadolinium | Terbium | Dysprosium | Holmium | Erbium | Thulium | Ytterbium | Lutetium | Thorium | Protactinium | Uranium | Neptunium | Plutonium | Americium | Curium | Berkelium | Californium | Einsteinium | Fermium | Mendelevium | Nobelium | Lawrencium
 deriving (Show, Eq)

data Token = LPar | RPar | Element Ele | NSym Integer
  deriving (Show, Eq)


lexer :: String -> [Token]
lexer "" = []
lexer ('(':s) = lexer s
lexer (')':s) = lexer s
lexer ('A':'c':s) = Element Actinium : lexer s
lexer ('A':'g':s) = Element Silver : lexer s
lexer ('A':'l':s) = Element Aluminium : lexer s
lexer ('A':'m':s) = Element Americium : lexer s
lexer ('A':'r':s) = Element Argon : lexer s
lexer ('A':'s':s) = Element Arsenic : lexer s
lexer ('A':'t':s) = Element Astatine : lexer s
lexer ('A':'u':s) = Element Gold : lexer s
lexer ('B':'a':s) = Element Barium : lexer s
lexer ('B':'e':s) = Element Beryllium : lexer s
lexer ('B':'h':s) = Element Bohrium : lexer s
lexer ('B':'i':s) = Element Bismuth : lexer s
lexer ('B':'k':s) = Element Berkelium : lexer s 
lexer ('B':'r':s) = Element Bromine : lexer s
lexer ('C':'a':s) = Element Calcium : lexer s
lexer ('C':'d':s) = Element Cadmium : lexer s
lexer ('C':'e':s) = Element Cerium : lexer s
lexer ('C':'f':s) = Element Californium : lexer s
lexer ('C':'l':s) = Element Chlorine : lexer s
lexer ('C':'m':s) = Element Curium : lexer s
lexer ('C':'n':s) = Element Copernicium : lexer s
lexer ('C':'o':s) = Element Cobalt : lexer s
lexer ('C':'r':s) = Element Chromium : lexer s
lexer ('C':'s':s) = Element Caesium : lexer s
lexer ('C':'u':s) = Element Copper : lexer s
lexer ('D':'b':s) = Element Dubnium : lexer s
lexer ('D':'y':s) = Element Dysprosium : lexer s
lexer ('E':'r':s) = Element Erbium : lexer s
lexer ('E':'s':s) = Element Einsteinium : lexer s 
lexer ('E':'u':s) = Element Europium : lexer s
lexer ('F':'e':s) = Element Iron : lexer s
lexer ('F':'l':s) = Element Flerovium : lexer s
lexer ('F':'m':s) = Element Fermium : lexer s
lexer ('F':'r':s) = Element Francium : lexer s
lexer ('G':'a':s) = Element Gallium : lexer s
lexer ('G':'d':s) = Element Gadolinium : lexer s
lexer ('G':'e':s) = Element Germanium : lexer s
lexer ('H':'e':s) = Element Helium : lexer s
lexer ('H':'f':s) = Element Hafnium : lexer s
lexer ('H':'g':s) = Element Mercury : lexer s
lexer ('H':'o':s) = Element Holmium : lexer s
lexer ('H':'s':s) = Element Hassium : lexer s
lexer ('I':'n':s) = Element Indium : lexer s
lexer ('I':'r':s) = Element Iridium : lexer s
lexer ('K':'r':s) = Element Krypton : lexer s
lexer ('L':'a':s) = Element Lanthanum : lexer s
lexer ('L':'i':s) = Element Lithium : lexer s
lexer ('L':'r':s) = Element Lawrencium : lexer s 
lexer ('L':'u':s) = Element Lutetium : lexer s
lexer ('L':'v':s) = Element Livermorium : lexer s
lexer ('M':'c':s) = Element Moscovium : lexer s
lexer ('M':'d':s) = Element Mendelevium : lexer s
lexer ('M':'g':s) = Element Magnesium : lexer s
lexer ('M':'n':s) = Element Manganese : lexer s
lexer ('M':'o':s) = Element Molybdenum : lexer s
lexer ('M':'t':s) = Element Meitnerium : lexer s
lexer ('N':'a':s) = Element Sodium : lexer s
lexer ('N':'b':s) = Element Niobium : lexer s
lexer ('N':'d':s) = Element Neodymium : lexer s
lexer ('N':'e':s) = Element Neon : lexer s
lexer ('N':'h':s) = Element Nihonium : lexer s
lexer ('N':'i':s) = Element Nickel : lexer s
lexer ('N':'o':s) = Element Nobelium : lexer s
lexer ('N':'p':s) = Element Neptunium : lexer s
lexer ('O':'g':s) = Element Oganesson : lexer s
lexer ('O':'s':s) = Element Osmium : lexer s
lexer ('P':'a':s) = Element Protactinium : lexer s
lexer ('P':'b':s) = Element Lead : lexer s
lexer ('P':'d':s) = Element Palladium : lexer s
lexer ('P':'r':s) = Element Promethium : lexer s
lexer ('P':'o':s) = Element Polonium : lexer s
lexer ('P':'u':s) = Element Plutonium : lexer s
lexer ('P':'t':s) = Element Platinum : lexer s
lexer ('R':'b':s) = Element Rubidium : lexer s
lexer ('R':'e':s) = Element Rhenium : lexer s
lexer ('R':'f':s) = Element Rutherfordium : lexer s
lexer ('R':'g':s) = Element Roentgenium : lexer s
lexer ('R':'h':s) = Element Rhodium : lexer s
lexer ('R':'a':s) = Element Radium : lexer s
lexer ('R':'n':s) = Element Radon : lexer s
lexer ('R':'u':s) = Element Ruthenium : lexer s
lexer ('S':'b':s) = Element Antimony : lexer s
lexer ('S':'c':s) = Element Scandium : lexer s
lexer ('S':'e':s) = Element Selenium : lexer s
lexer ('S':'g':s) = Element Seaborgium : lexer s
lexer ('S':'i':s) = Element Silicon : lexer s
lexer ('S':'m':s) = Element Samarium : lexer s
lexer ('S':'n':s) = Element Tin : lexer s
lexer ('S':'r':s) = Element Strontium : lexer s
lexer ('T':'a':s) = Element Tantalum : lexer s
lexer ('T':'b':s) = Element Terbium : lexer s
lexer ('T':'c':s) = Element Technetium : lexer s
lexer ('T':'e':s) = Element Tellurium : lexer s
lexer ('T':'h':s) = Element Thorium : lexer s
lexer ('T':'i':s) = Element Titanium : lexer s
lexer ('T':'m':s) = Element Thulium : lexer s
lexer ('T':'s':s) = Element Tennessine : lexer s
lexer ('X':'e':s) = Element Xenon : lexer s
lexer ('Y':'b':s) = Element Ytterbium : lexer s
lexer ('Z':'n':s) = Element Zinc : lexer s
lexer ('Z':'r':s) = Element Zirconium : lexer s
lexer ('B':s) = Element Boron : lexer s
lexer ('C':s) = Element Carbon : lexer s
lexer ('F':s) = Element Fluorine : lexer s
lexer ('H':s) = Element Hydrogen : lexer s
lexer ('I':s) = Element Iodine : lexer s
lexer ('K':s) = Element Potassium : lexer s
lexer ('N':s) = Element Nitrogen : lexer s
lexer ('O':s) = Element Oxygen : lexer s
lexer ('P':s) = Element Phosphorus : lexer s
lexer ('S':s) = Element Sulfur : lexer s
lexer ('U':s) = Element Uranium : lexer s
lexer ('V':s) = Element Vanadium : lexer s
lexer ('W':s) = Element Tungsten : lexer s
lexer ('Y':s) = Element Yttrium : lexer s
lexer (c:s) | isDigit c =
  let (num,rst) = span isDigit s
   in NSym (read (c:num)) : lexer rst
lexer (c:s) | isSpace c = lexer s
lexer s = error ("Lexical error: " ++ s)

parser :: [Token] -> [Chemical]
parser (Element e : NSym x : s) = Mul (e) (x) : parser s
parser (Element e : s) =  Sing (e) : parser s
parser (NSym x : s) = Sub (x) : parser s
parser [] = []

eval :: [Chemical] -> Integer
eval [] = 0
eval (Sing x : s) = (lookUp x) + (eval s)
eval (Mul x y : s) = (y * (lookUp x)) + (eval s)

lookUp :: Ele -> Integer
lookUp Hydrogen = 1
lookUp Helium = 4
lookUp Lithium = 7
lookUp Beryllium = 9
lookUp Boron = 11
lookUp Carbon = 12
lookUp Nitrogen = 14
lookUp Oxygen = 16
lookUp Fluorine = 19
lookUp Neon = 20
lookUp Sodium = 23
lookUp Magnesium = 24
lookUp Aluminium = 27
lookUp Silicon = 28
lookUp Phosphorus = 31
lookUp Sulfur = 32
lookUp Chlorine = 35
lookUp Argon = 40
lookUp Potassium = 39
lookUp Calcium = 40
lookUp Scandium = 45
lookUp Titanium = 48
lookUp Vanadium = 51
lookUp Chromium = 52
lookUp Manganese = 55
lookUp Iron = 56
lookUp Cobalt = 59
lookUp Nickel = 59
lookUp Copper = 64
lookUp Zinc = 65
lookUp Gallium = 70
lookUp Germanium = 73
lookUp Arsenic = 75
lookUp Selenium = 79
lookUp Bromine = 80
lookUp Krypton = 84
lookUp Rubidium = 85
lookUp Strontium = 88
lookUp Yttrium = 89
lookUp Zirconium = 91
lookUp Niobium = 93
lookUp Molybdenum = 96
lookUp Technetium = 98
lookUp Ruthenium = 101
lookUp Rhodium = 103
lookUp Palladium = 106
lookUp Cadmium = 112
lookUp Indium = 115
lookUp Antimony = 122
lookUp Tellurium = 128
lookUp Iodine = 127
lookUp Xenon = 131
lookUp Caesium = 133
lookUp Barium = 137
lookUp Lanthanum = 139
lookUp Hafnium = 178
lookUp Tantalum = 181
lookUp Tungsten = 184
lookUp Rhenium = 186
lookUp Osmium = 190
lookUp Iridium = 192
lookUp Platinum = 195
lookUp Gold = 197
lookUp Mercury = 201
lookUp Lead = 207
lookUp Bismuth = 209
lookUp Polonium = 209
lookUp Astatine = 210
lookUp Radon = 222
lookUp Francium = 223
lookUp Radium = 226
lookUp Actinium = 227
lookUp Rutherfordium = 261
lookUp Dubnium = 262
lookUp Seaborgium = 269
lookUp Bohrium = 264
lookUp Hassium = 269
lookUp Meitnerium = 278
lookUp Darmstadtium = 281
lookUp Roentgenium = 282
lookUp Copernicium = 285
lookUp Nihonium = 286
lookUp Flerovium = 289
lookUp Moscovium = 289
lookUp Livermorium = 293
lookUp Tennessine = 294
lookUp Oganesson = 294
lookUp Cerium = 140
lookUp Praseodymium = 141
lookUp Neodymium = 144
lookUp Promethium = 145
lookUp Samarium = 150
lookUp Europium = 152
lookUp Gadolinium = 157
lookUp Terbium = 159
lookUp Dysprosium = 163
lookUp Holmium = 165
lookUp Erbium = 167
lookUp Thulium = 169
lookUp Ytterbium = 173
lookUp Lutetium = 175
lookUp Thorium = 232
lookUp Protactinium = 231
lookUp Uranium = 238
lookUp Neptunium = 237
lookUp Plutonium = 244
lookUp Americium = 243
lookUp Curium = 247
lookUp Berkelium = 247
lookUp Californium = 251
lookUp Einsteinium = 252
lookUp Fermium = 257
lookUp Mendelevium = 258
lookUp Nobelium = 259
lookUp Lawrencium = 262

main :: IO ()
main = loop []

add :: String -> String -> Float
add ('.':xs) ('.':ys) = (read ("0" ++ xs)) + (read ("0" ++ ys))
add ('.':xs) y = (read ("0" ++ xs)) + (read y)
add x ('.':ys) = (read x) + (read ("0" ++ ys))
add x y = read x + read y

sub :: String -> String -> Float
sub ('.':xs) ('.':ys) = (read ("0" ++ xs)) - (read ("0" ++ ys))
sub ('.':xs) y = (read ("0" ++ xs)) - (read y)
sub x ('.':ys) = (read x) - (read ("0" ++ ys))
sub x y = (read x) - (read y)

mul :: String -> String -> Float
mul ('.':xs) ('.':ys) = (read ("0" ++ xs)) * (read ("0" ++ ys))
mul ('.':xs) y = (read ("0" ++ xs)) * (read y)
mul x ('.':ys) = (read x) * (read ("0" ++ ys))
mul x y = (read x) * (read y)

divi :: String -> String -> Float
divi ('.':xs) ('.':ys) = divFloat (read ("0" ++ xs)) (read ("0" ++ ys))
divi ('.':xs) y = divFloat (read ("0" ++ xs)) (read y)
divi x ('.':ys) = divFloat (read x) (read ("0" ++ ys))
divi x y = divFloat (read x) (read y)

divFloat :: Float -> Float -> Float
divFloat x y = x / y

modu :: String -> String -> Int
modu x y = modul (read x) (read y)

modul :: Int -> Int -> Int
modul x y = x `mod` y

expi :: String -> String -> Float
expi ('.':xs) ('.':ys) = (read ("0" ++ xs)) ^ (read ("0" ++ ys))
expi ('.':xs) y = (read ("0" ++ xs)) ^ (read y)
expi x ('.':ys) = (read x) ^ (read ("0" ++ ys))
expi x y = (read x) ^ (read y)

decimalToBinary :: String -> String
decimalToBinary x = "0" ++ reverse (binary (read x))

binary :: Int -> String
binary 0 = "0"
binary 1 = "1"
binary x | x `mod` 2 == 0 = "0" ++ binary (x `div` 2)
         | x `mod` 2 == 1 = "1" ++ binary (x `div` 2)

decimalToOctual :: String -> String
decimalToOctual x = reverse (octual (read x))

octual :: Int -> String
octual 0 = "0"
octual 1 = "1"
octual 2 = "2"
octual 3 = "3"
octual 4 = "4"
octual 5 = "5"
octual 6 = "6"
octual 7 = "7"
octual x | x `mod` 8 == 0 = "0" ++ octual (x `div` 8)
         | x `mod` 8 == 1 = "1" ++ octual (x `div` 8)
         | x `mod` 8 == 2 = "2" ++ octual (x `div` 8)
         | x `mod` 8 == 3 = "3" ++ octual (x `div` 8)
         | x `mod` 8 == 4 = "4" ++ octual (x `div` 8)
         | x `mod` 8 == 5 = "5" ++ octual (x `div` 8)
         | x `mod` 8 == 6 = "6" ++ octual (x `div` 8)
         | x `mod` 8 == 7 = "7" ++ octual (x `div` 8)

decimalToHexadecimal :: String -> String
decimalToHexadecimal x = reverse (hexadecimal (read x))

hexadecimal :: Int -> String
hexadecimal 0 = "0"
hexadecimal 1 = "1"
hexadecimal 2 = "2"
hexadecimal 3 = "3"
hexadecimal 4 = "4"
hexadecimal 5 = "5"
hexadecimal 6 = "6"
hexadecimal 7 = "7"
hexadecimal 8 = "8"
hexadecimal 9 = "9"
hexadecimal 10 = "A"
hexadecimal 11 = "B"
hexadecimal 12 = "C"
hexadecimal 13 = "D"
hexadecimal 14 = "E"
hexadecimal 15 = "F"
hexadecimal x | x `mod` 16 == 0 = "0" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 1 = "1" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 2 = "2" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 3 = "3" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 4 = "4" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 5 = "5" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 6 = "6" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 7 = "7" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 8 = "8" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 9 = "9" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 10 = "A" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 11 = "B" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 12 = "C" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 13 = "D" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 14 = "E" ++ hexadecimal (x `div` 16)
              | x `mod` 16 == 15 = "F" ++ hexadecimal (x `div` 16)

              
loop :: String -> IO ()
loop def = do
  cmd <- getLine
  case words cmd of
    ["help"] -> do 
       putStrLn "This calculator is a special calculator that convert a number from decimal to binary, octual, and hexadecimal."
       putStrLn "This also calculates Molecule Mass"
    ["add",x,y] -> print (add x y) >> loop def
    ["sub",x,y] -> print (sub x y) >> loop def
    ["mul",x,y] -> print (mul x y) >> loop def
    ["div",x,y] -> print (divi x y) >> loop def
    ["mod",x,y] -> print (modu x y) >> loop def
    ["exp",x,y] -> print (expi x y) >> loop def

    ["binary", x] -> print (decimalToBinary x) >> loop def
    ["oct", x] -> print (decimalToOctual x) >> loop def
    ["hexadecimal", x] -> print (decimalToHexadecimal x) >> loop def
    ["MolecularMass", x] -> print (eval (parser (lexer x))) >> loop def

    ["quit"] -> return ()
    _ -> putStrLn "Command unrecognized, try again" >> loop def

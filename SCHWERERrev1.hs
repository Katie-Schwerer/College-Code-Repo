radius :: Double -> Double -> Double
radius x y = sqrt ((x*x) + (y*y))

radius' :: (Double, Double) -> Double
radius' (x,y) = sqrt ((x*x) + (y*y))

sumEvens :: Integer -> Integer
sumEvens 0 = 0
sumEvens 2 = 2
sumEvens n = if n < 0 then 0 else (if n `mod` 2 == 0 then n + sumEvens (n-1) else sumEvens (n-1))

sumEvens' :: Integer -> Integer
sumEvens' 0 = 0
sumEvens' 2 = 2
sumEvens' n = if n < 0 then 0 else (if even n then n + sumEvens (n-1) else sumEvens (n-1))

collatz :: Integer -> Integer
collatz 0 = 1
collatz 1 = 1
collatz n = if even n then collatz (div n 2) + 1 else collatz(3*n + 1) + 1

collatzCheck :: [Integer]
collatzCheck = [collatz x | x <- [1..100]]

multiplesOfFive :: [Integer]
multiplesOfFive = [ x | x <- [1..100], x `mod` 5 == 0]

init' :: [a] -> [a]
init' x = if null x /= True then take (length x - 1) x else []

findEmpty :: [String] -> Bool
findEmpty [] = True
findEmpty x = if "" `elem` x then True else False


getLengths :: [String] -> [Int]
getLengths x = map length x
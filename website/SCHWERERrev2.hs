--1
minList :: [Integer] -> Integer
minList [x] = x
minList (x:xs) = min x (minList xs)
--2
addAbs :: [Integer] -> Integer
addAbs [] = 0
addAbs (x:xs) = addAbs xs + abs x
--3
existsOdd :: [Integer] -> Bool
existsOdd [] = False
existsOdd (x:xs) | odd x = True
existsOdd (x:xs) = existsOdd xs
--4
findOdd :: [Integer] -> Maybe Integer
findOdd [] = Nothing
findOdd (x:xs) | odd x = Just x
findOdd (x:xs) = findOdd xs
--5
removeEmpty :: [String] -> [String]
removeEmpty [] = []
removeEmpty (x:xs) | length x == 0 = removeEmpty xs
removeEmpty (x:xs) = x : removeEmpty xs
--6
subtractEach :: [(Integer,Integer)] -> [Integer]
subtractEach [] = []
subtractEach ((x,y):ps) = x - y : subtractEach ps 
--7
makeGreeting :: Maybe String -> String
makeGreeting Nothing = "Hello!" 
makeGreeting (Just x) = "Hello, " ++ x ++ "!"
--8
catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Nothing:es) = catMaybes es
catMaybes (Just x:es) = x : catMaybes es
--9
classify :: [Either a b] -> ([a],[b])
classify [] = ([],[])
classify x = (leftList x, rightList x)

leftList :: [Either a b] -> [a]
leftList [] = []
leftList (Left x:xs) = x : leftList xs
leftList (Right y:xs) = leftList xs

rightList :: [Either a b] -> [b]
rightList [] = []
rightList ((Right y):ys) = y : rightList ys
rightList (Left x:ys) = rightList ys
--10
isPrefix :: (Eq a) => [a] -> [a] -> Bool
isPrefix []_ = True
isPrefix _[] = False
isPrefix (x:xs) (y:ys) 
       | x /= y  = False
      | otherwise = isPrefix xs ys
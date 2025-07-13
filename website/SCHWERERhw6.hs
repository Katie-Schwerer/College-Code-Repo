data LTree a = LLeaf a | LNode a (LTree a) (LTree a) deriving Show

foldTree :: (a -> b -> b -> b) -> (a -> b) -> LTree a -> b
foldTree comb base (LLeaf x) = base x
foldTree comb base (LNode y t1 t2) = comb y (foldTree comb base t1) (foldTree comb base t2)

getLeaves :: LTree a -> [a]
getLeaves (LLeaf a) =  [a]
getLeaves (LNode x t1 t2) = getLeaves t1 ++ getLeaves t2

countNodes :: LTree a -> Integer
countNodes (LLeaf x) = 0
countNodes (LNode x t1 t2) = countNodes t1 + countNodes t2 + 1

sumTree :: LTree Integer -> Integer
sumTree (LLeaf x) =  x
sumTree (LNode x t1 t2) = x + (sumTree t1) + (sumTree t2)

occursInLeaves :: (a -> Bool) -> LTree a -> Bool
occursInLeaves f (LLeaf x) = f x == True
occursInLeaves f (LNode x t1 t2) =  (occursInLeaves f t1)  || (occursInLeaves f t2)

checkNoCover :: (Eq a) => a -> LTree a -> Bool
checkNoCover x (LLeaf y) = y == x
checkNoCover x (LNode y t1 t2) | x == y = False
                               | otherwise = (checkNoCover x t1) || (checkNoCover x t2)

getLeaves' :: LTree a -> [a]
getLeaves' t = foldTree (\x tl tr -> tl ++ tr) (\y -> [y]) t

countNodes' :: LTree a -> Integer
countNodes' t = foldTree (\x tl tr -> 1 + tl + tr) (\y -> 0) t

sumTree' :: LTree Integer -> Integer
sumTree' t = foldTree (\x t1 tr -> x + t1 + tr) (\y -> y) t

occursInLeaves' :: (a -> Bool) -> LTree a -> Bool
occursInLeaves' f t = foldTree (\x tl tr -> tl || tr) (\y -> f y == True) t

checkNoCover' :: (Eq a) => a -> LTree a -> Bool
checkNoCover' x t = foldTree (\y tl tr -> if x == y then False else tl || tr) (\z -> z == x) t
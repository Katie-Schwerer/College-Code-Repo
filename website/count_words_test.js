const ct = require("count_things");
console.log("Expected: 17 6 5 3");
console.log(ct.countChar1("\t hi bob bob bob dealer \t"));
console.log(ct.countChar2("hi bob bob bob dealer","b"));
console.log(ct.countWords1(" hi bob bob\tbob dealer "));
console.log(ct.countWords2(" hi bob bob \t bob aboby bobbob dealer ", "bob"));


    let count = instr.split(chr).length;
    return count - 1;
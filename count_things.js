function countChar1(instr) {
  let count = 0;
  for(let i = 0; i < instr.length; i++) {
    if(instr.charAt(i) != " " && instr.charAt(i) != '\t') {
      count += 1;
    }
  }
  return count;
}

function countChar2(instr, chr) {
  let count = 0;
  for(let i = 0; i < instr.length; i++) {
    if(instr.charAt(i) == chr) {
      count += 1;
    }
  }
  return count;
}

function countWords1(instr) {
    instr = instr.replace(/\t/g,' ');
    if(instr == '') {
      return 0;
    }
    return instr.trim().split(' ').length;
}

function countWords2(instr, wordstr) {
    instr = instr.replace(/\t/g,' ');
    let len = instr.trim().split(' ');
    let count = 0;
    for(let i = 0; i < len.length; i++) {
      if(len[i] == wordstr) {
        count += 1;
      }
    }
    return count;
}

//Make functions public.  Very important.
exports.countChar1 = countChar1;
exports.countChar2 = countChar2;
exports.countWords1 = countWords1;
exports.countWords2 = countWords2;
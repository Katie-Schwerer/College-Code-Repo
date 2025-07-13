
	;The following should print Hello-World
.orig x3000
LEA R0, STR1 ;R0 points to STR1
LEA R1, STR2 ;R1 points to STR2
JSR STRCAT ;Concatenate STR2 to the end of STR1
PUTS ;Print STR1 now
HALT
;Data
.blkw 100 ;Change this value when testing. Vary from 50 to 150.
STR1 .stringz "Hello-" ;And, of course, change the strings
.blkw 100 ;Change this value when testing. Vary from 50 to 150.
STR2 .stringz "World\n" ;And, of course, change this one too
;*************************SUBROUTINES*******************************
ST R0, SR0
	ST R1, SR1
	ST R4, SR4
	ST R7, SR7

    ADD R1, R0, #0
	LDR R0, R1, #1
LP_TH  
	BRZ DONE
         
	ADD R1, R1, #1
    LDR R0, R1, #0
	
	BRNZP LP_TH

DONE  


;Start of the program
	.orig x3000
    LEA R0, PROMPT
    PUTS
    JSR GETALPHA
    JSR GETALPHA
    HALT
PROMPT .stringz "Enter two letters:" 

GETALPHA
        ST R1, SR1
		ST R7, SR7
GC      GETC
		ADD R1, R0, #-10
		BRZ res
		ADD R1, R1, #-15
		ADD R1, R1, #-15
		ADD R1, R1, #-15
		ADD R1, R1, #-15
		ADD R1, R1, #-15
		ADD R1, R1, #-10
		BRZ print
		AND R1, R1, #0
		ADD R1, R0, #-15
		ADD R1, R1, #-15
		ADD R1, R1, #-10
		ADD R1, R1, #-1
		BRZP GC
		ADD R1, R1, #-15
		ADD R1, R1, #-10
		BRN print
		ADD R1, R1, #-6
		BRZP GC
		ADD R1, R1, #-15
		ADD R1, R1, #-10
		BRN print
		ADD R1, R0, #-1
		BRP GC
print   OUT
res     LD R1, SR1
        LD R7, SR7
        RET		
SR1     .fill x0000
SR7     .fill x0000



        .end
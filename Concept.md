#Network docu
	Byte 1: Cmd ->	0x01 = Draw a Pixel
					0x02 = Draw a Rectangle
					0x03 = Draw a Line
					0x04 = Draw a Circle
					0x05 = Draw a Text
	 
	 Byte 2 - 4: Color -> r, g, b
	 
	 Byte 5 - 8: X-Pos. 1 | Int
	 
	 Byte 9 - 12: Y-Pos. 1 | Int
	 
	 Byte 13 - 20 (bei Rec): width, height | Int
	 
	 Byte 13 - 20 (bei Line): x2, y2 | Int
	 
	 Byte 13 - 16 (bei Circle): Radius | Int
	 
	 Byte 14f (je 2, bei Text): Chars | Char
	 
	 Byte 13 (bei Text): font size
	 
	 Byte 21 (bei Line): Line Dicke

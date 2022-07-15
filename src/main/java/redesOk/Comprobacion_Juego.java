package redesOk;

public class Comprobacion_Juego {
	public static int check_lateral_right(String[][] tabla,int x,int y) {
		int limit = tabla[0].length;
		int axis = x;
		int orde = y;
		int cont = 0;
		
		if(orde != limit-1) {
			while(orde+1<limit && tabla[axis][orde+1].equals(tabla[x][y])) {
				System.out.println("Detectado check_lateral_left");
				cont = cont + 1;
				orde = orde + 1;	
			}
		}
		return cont;
	}
	
	
	public static int check_lateral_left(String[][] tabla,int x,int y) {
		int limit =0;
		int axis = x;
		int orde = y;
		int cont = 0;
		if(orde != limit) {
			while(orde-1>=limit && tabla[axis][orde-1].equals(tabla[x][y])) {
				System.out.println("Detectado check_lateral_left");
				cont = cont + 1;
				orde = orde - 1;	
			}
		}
		return cont;
	}
	
	public static int check_vertical_up(String[][] tabla,int x,int y) {
	int	limit = 0;
	int	axis = x;
	int	orde = y; 
	int	cont=0;

		if (axis != limit) {
			//print("Entra al if")
			while(axis-1>=limit && tabla[axis-1][orde].equals(tabla[x][y])) {
				System.out.println("Detectado check_vertical_up axis="+axis+ "orde="+orde+" celda="+tabla[axis][orde]);
				cont = cont + 1	;
				axis = axis - 1	;
			}
		}
		return cont;
	
}
	
	
public static int check_vertical_down(String[][] tabla,int x,int y) {
	int	limit = tabla.length;
	int	axis = x;
	int	orde = y; 
	int	cont=0;
		//print(f"Detectado,axis={axis} orde={orde} {table[axis-1][orde]} y  {table[x][y]}")

		if (axis != limit-1) {
			System.out.println("Verificando: tabla[axis+1][orde]="+tabla[axis+1][orde]+" tabla[x][y]="+tabla[x][y]);
			System.out.println(axis+1<limit);
			System.out.println(tabla[axis+1][orde].equals(tabla[x][y]));
			while(axis+1<limit && tabla[axis+1][orde].equals(tabla[x][y])) {
				System.out.println("Detectado check_vertical_down axis="+axis+ "orde="+orde+" celda="+tabla[axis][orde]);
				cont = cont + 1	;
				axis = axis + 1	;
			}
		}
		return cont;
	
}


public static int check_neg_diag_down(String[][] tabla,int x,int y) {
	int limit_x = tabla.length;
	int limit_y = tabla[0].length;
	int axis = x;
	int orde = y;
	int cont=0;
	//print(f"Detectado,axis={axis} orde={orde} {table[axis][orde-1]} y  {table[x][y]}")

	if (axis != limit_x-1 && orde != limit_y-1) {
		//print("Entra al if")
		while (axis+1<limit_x && orde+1<limit_y && tabla[axis+1][orde+1].equals(tabla[x][y])) {
			//print(f"Detectado,axis={axis} orde={orde} {table[axis][orde-1]} y  {table[x][y]}")
			cont = cont + 1;
			axis = axis +1;
			orde = orde +1;
		}
	}
	return cont;
}
		
public static int check_neg_diag_up(String[][] tabla,int x,int y) {
	int limit_x = 0;
	int limit_y = 0;
	int axis = x;
	int orde = y;
	int cont=0;
	//print(f"Detectado,axis={axis} orde={orde} {table[axis][orde-1]} y  {table[x][y]}")

	if (axis != limit_x && orde != limit_y) {
		//print("Entra al if")
		while (axis-1>=limit_x && orde-1>=limit_y && tabla[axis-1][orde-1].equals(tabla[x][y])) {
			//print(f"Detectado,axis={axis} orde={orde} {table[axis][orde-1]} y  {table[x][y]}")
			cont = cont + 1;	
			axis = axis -1;
			orde = orde -1;
		}
	}
	return cont	;

}


public static int check_pos_diag_down(String[][] tabla,int x,int y) {
	int limit_x = tabla.length;
	int limit_y = 0;
	int axis = x;
	int orde = y;
	int cont=0;
	//print(f"Detectado,axis={axis} orde={orde} {table[axis][orde-1]} y  {table[x][y]}")

	if(axis != limit_x-1 && orde != limit_y) {
		//print("Entra al if")
		while (axis+1<limit_x && orde-1>=limit_y && tabla[axis+1][orde-1].equals(tabla[x][y])){
			//print(f"Detectado,axis={axis} orde={orde} {table[axis][orde-1]} y  {table[x][y]}")
			cont = cont + 1;	
			axis = axis +1;
			orde = orde -1;
		}
	}
	return cont;
}
	
public static int check_pos_diag_up(String[][] tabla,int x,int y) {
	int limit_x = 0;
	int limit_y = tabla[0].length;
	int axis = x;
	int orde = y;
	int cont=0;

	if (axis != limit_x-1 && orde != limit_y) {
		while (axis-1>=limit_x && orde+1<limit_y && tabla[axis-1][orde+1].equals(tabla[x][y])) {
			cont = cont + 1;
			axis = axis - 1;
			orde = orde + 1;
		}
	}
	return cont;	
}


public static boolean check_winner(String[][] tabla,int x,int y,int n) {

	if(check_lateral_left(tabla,x,y)+check_lateral_right(tabla,x,y)==n-1) { return true;}
	if(check_vertical_up(tabla,x,y)+check_vertical_down(tabla,x,y)==n-1) {return true;}
	if(check_neg_diag_down(tabla,x,y)+check_neg_diag_up(tabla,x,y)==n-1) {return true;}
	if(check_pos_diag_up(tabla,x,y)+check_pos_diag_down(tabla,x,y)==n-1) {return true;}

	return false;
}


}




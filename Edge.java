public  class Edge{
		public Vert x;
		public Vert y;
		public int weight;
		//public boolean visited = false;
		public Edge(int a,int b,int weight){
			this.weight = weight;
			this.x = new Vert(a);
			this.y = new Vert(b);

			
		}

	}

	var i=0;
		function show1(){
			if(i==0){
				document.getElementById("1").style.display="block";
				document.getElementById("2").style.display="block";
				document.getElementById("3").style.display="block";
				document.getElementById("4").style.display="block";
				i=1;
			}else if(i==1){
				document.getElementById("1").style.display="none";
				document.getElementById("2").style.display="none";
				document.getElementById("3").style.display="none";
				document.getElementById("4").style.display="none";
				i=0;
			}
		}

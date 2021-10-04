$(document).ready(function(){
	// Set up array to track used squares
	var boardChoices = [1, 2, 3, 4, 5, 6, 7, 8, 9];
	var turnCount = 0;

	// Player starts out as "X" and  plays the computer by default
	var player = $("input:radio[name=player]:checked").val();
	var opponent = $("input:radio[name=opponent]:checked").val();

	// Capture Player change
	$("input:radio[name=player]").click(function() {
   		player = $(this).val();
	});

	// Capture Opponent change
	$("input:radio[name=opponent]").click(function() {
   		opponent = $(this).val();
	});

	// Reset button listener
	$("#reset").click(function(){
		gameReset();
	});

	// Set up Click Listener for each Square
	// the function will validate the square, set it to the player
	// and check for a winner
	for (var i=1; i<=9; i++){
		$("#cell"+i).on("click", function (){
			// Square can only be set once
			if ($(this).hasClass("bg1") || $(this).hasClass("bg2")) {
					alert("Allready in use!!");
			} else {
				// Set the square to the appropriate player
				if (player ==="X") {
					$(this).addClass("bg1");
				} else {
					// Player is "O"
					$(this).addClass("bg2");
				}
				// Remove square from available choices
				var id = $(this).attr("id");
				var indexValue = parseInt(id.substr(-1));
				var index = boardChoices.indexOf(indexValue);
				boardChoices.splice(index, 1);
				// Check if player wins
				player = playerTurn(player);
			}
		}); // end on click
	} // end for loop

	function playerTurn (player) {
		turnCount ++;
		if (player ==="X") {
			win = checkForWinner("bg1");
			if (win){
				alert("X is the WINNER!!");
				gameReset();
			}
			$('#r1').prop("checked", false);
			$('#r2').prop("checked", true);
			player = "O";
			// Computer turn if playing computer
			if (opponent === "computer"){
				 player = computerTurn(player);
			}
		} else {
			// Player is "O"
			win = checkForWinner("bg2");
			if (win){
				alert("O is the WINNER!!");
				gameReset();
			}
			$('#r1').prop("checked", true);
			$('#r2').prop("checked", false);
			player = "X";
			// Computer turn if playing computer
			if (opponent === "computer"){
				player = computerTurn(player);
			}
		}
		return player;
	}

	function computerTurn(player) {
		turnCount ++;
		// Pick an available square
		var rs = boardChoices[Math.floor(Math.random() * boardChoices.length)];
		// Set player token and check for winner
		if (player ==="X") {
			$("#cell"+rs).addClass("bg1");
			win = checkForWinner("bg1");
			$('#r1').prop("checked", false);
			$('#r2').prop("checked", true);
			player = "O";
		} else {
			// Player is "O"
			$("#cell"+rs).addClass("bg2");
			win = checkForWinner("bg2");
			$('#r1').prop("checked", true);
			$('#r2').prop("checked", false);
			player = "X";
		}
		// Did computer win?
		if (win){
			alert("SKYNET TAKING OVER");
			gameReset();
		}
		// Remove square from available list
		var indexValue = parseInt(rs);
		var index = boardChoices.indexOf(indexValue);
		boardChoices.splice(index, 1);
		return player;
	}

	function checkForWinner(elClass){
		// Check rows
		if ($("#cell1").hasClass(elClass) && $("#cell2").hasClass(elClass) &&
				$("#cell3").hasClass(elClass)) {
			return true;
		}
		if ($("#cell4").hasClass(elClass) && $("#cell5").hasClass(elClass) &&
				$("#cell6").hasClass(elClass)) {
			return true;
		}
		if ($("#cell7").hasClass(elClass) && $("#cell8").hasClass(elClass) &&
				$("#cell9").hasClass(elClass)) {
			return true;
		}
		// Check Colummns
		if ($("#cell1").hasClass(elClass) && $("#cell4").hasClass(elClass) &&
				$("#cell7").hasClass(elClass)) {
			return true;
		}
		if ($("#cell2").hasClass(elClass) && $("#cell5").hasClass(elClass) &&
				$("#cell8").hasClass(elClass)) {
			return true;
		}
		if ($("#cell3").hasClass(elClass) && $("#cell6").hasClass(elClass) &&
				$("#cell9").hasClass(elClass)) {
			return true;
		}
		// Check Diagonal
		if ($("#cell1").hasClass(elClass) && $("#cell5").hasClass(elClass) &&
				$("#cell9").hasClass(elClass)) {
			return true;
		}
		if ($("#cell3").hasClass(elClass) && $("#cell5").hasClass(elClass) &&
				$("#cell7").hasClass(elClass)) {
			return true;
		}
		if (turnCount === 9) {
			alert("TIE!!");
			gameReset();
		}
	}

	function gameReset(){
		for (var i=1; i<=9; i++){
			$("#cell"+i).removeClass('bg1 bg2');
		}
		boardChoices = [1, 2, 3, 4, 5, 6, 7, 8, 9];
		turnCount = 0;
		var player = $("input:radio[name=player]:checked").val();
	}



});
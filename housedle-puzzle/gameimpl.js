var num_moves = 0;
var indexes = ['p11', 'p12', 'p13', 'p21', 'p22', 'p23', 'p31', 'p32', 'p33'];
var dict = {
  11: 0,
  12: 1,
  13: 2,
  21: 3,
  22: 4,
  23: 5,
  31: 6,
  32: 7,
  33: 8
};

var image_chosen = Math.floor(Math.random() * 10) + 1;;
var str_img = 'day' + image_chosen.toString();

function start_game() {
  var tab_position = new Array();
  while (tab_position.length < 9) {
      var numero = Math.round(Math.random() * 100);
      if ((numero == 11) || (numero == 12) || (numero == 13) || (numero == 21) || (numero == 22) || (numero == 23) || (numero == 31) || (numero == 32) || (numero == 33)) {
        if (!tab_position.includes(numero)) {
          tab_position[tab_position.length] = numero;
        }
      }
  }
  console.log(is_solvable(tab_position));
  initialise(tab_position);
  document.getElementById('moves').textContent = "Moves: 0";
}

function switching(cell) {
  if (document.getElementById(cell).textContent != '11') {
    switch (cell) {
      case 'p11':
        if (document.getElementById('p12').textContent == '11') {
         move_right('p11', 'p12');
        }

        if (document.getElementById('p21').textContent == '11') {
          move_down('p11', 'p21');
        }

        if (check_win()) {
          console.log("Done");
          setTimeout(show_image, 500);
        }
      break;

      case 'p12':
        if (document.getElementById('p11').textContent == '11') {
          move_left('p11', 'p12');
        }
        if (document.getElementById('p22').textContent == '11') {
          move_down('p12', 'p22');
        }
        if (document.getElementById('p13').textContent == '11') {
          move_right('p12', 'p13');
        }
      break;

      case 'p13':
        if (document.getElementById('p12').textContent == '11') {
          move_left('p12', 'p13');
        }
        if (document.getElementById('p23').textContent == '11') {
          move_down('p13', 'p23');
        }
      break;

      case 'p21':
        if (document.getElementById('p11').textContent == '11') {
          move_up('p11', 'p21');
        }
        if (document.getElementById('p22').textContent == '11') {
          move_left('p21', 'p22');
        }
        if (document.getElementById('p31').textContent == '11') {
          move_down('p21', 'p31');
        }
      break;

      case 'p22':
        if (document.getElementById('p12').textContent == '11') {
          move_up('p12', 'p22');
        }
        if (document.getElementById('p32').textContent == '11') {
          move_down('p22', 'p32');
        }
        if (document.getElementById('p21').textContent == '11') {
          move_left('p21', 'p22');
        }
        if (document.getElementById('p23').textContent == '11') {
          move_right('p22', 'p23');
        }
      break;

      case 'p23':
        if (document.getElementById('p13').textContent == '11') {
          move_up('p13', 'p23');
        }
        if (document.getElementById('p33').textContent == '11') {
          move_down('p23', 'p33');
        }
        if (document.getElementById('p22').textContent == '11') {
          move_left('p22', 'p23');
        }
      break;

      case 'p31':
        if (document.getElementById('p21').textContent == '11') {
          move_up('p21', 'p31');
        }
        if (document.getElementById('p32').textContent == '11') {
          move_right('p31', 'p32');
        }
      break;

      case 'p32':
        if (document.getElementById('p22').textContent == '11') {
          move_up('p22', 'p32');
        }
        if (document.getElementById('p31').textContent == '11') {
          move_left('p31', 'p32');
        }
        if (document.getElementById('p33').textContent == '11') {
          move_right('p32', 'p33');
        }
      break;

      case 'p33':
        if (document.getElementById('p23').textContent == '11') {
          move_up('p23', 'p33');
        }
        if (document.getElementById('p32').textContent == '11') {
          move_left('p32', 'p33');
        }
      break;
    }
  }
}

function check_win() {
  panel = [];
  for (let i = 0; i < indexes.length; i++) {
    panel[i] = document.getElementById(indexes[i]).textContent;
  }
  console.log(panel);
  if (panel == ['11', '12', '13', '21', '22', '23', '31', '32', '33']) return true;
  // if ((panel[0] == '11') && (panel[1] == '12') && (panel[2] == '23') && (panel[4] == '21') && (panel[5] == '22') && (panel[6] == '23') && (panel[7] == '31') && (panel[8] == '32') && (panel[9] == '33')) {
  return false;
}

function move_right(left, right) {
  var temp_val = document.getElementById(right).textContent;
  document.getElementById(right).textContent = document.getElementById(left).textContent;
  document.getElementById(left).textContent = temp_val;
  document.getElementById(right).style.background = 'url(assets/day4/' + document.getElementById(right).textContent + '.jpg)';
  document.getElementById(left).style.background = 'url(assets/day4/' + document.getElementById(left).textContent + '.jpg)';
  increment_move();
}

function move_down(up, down) {
  var temp_val = document.getElementById(down).textContent;
  document.getElementById(down).textContent = document.getElementById(up).textContent;
  document.getElementById(up).textContent = temp_val;
  document.getElementById(up).style.background = 'url(assets/day4/' + document.getElementById(up).textContent + '.jpg)';
  document.getElementById(down).style.background = 'url(assets/day4/' + document.getElementById(down).textContent + '.jpg)';
  increment_move();
}

function move_left(left, right) {
  var temp_val = document.getElementById(left).textContent;
  document.getElementById(left).textContent = document.getElementById(right).textContent;
  document.getElementById(right).textContent = temp_val;
  document.getElementById(left).style.background = 'url(assets/day4/' + document.getElementById(left).textContent + '.jpg)';
  document.getElementById(right).style.background = 'url(assets/day4/' + document.getElementById(right).textContent + '.jpg)';
  increment_move();
}

function move_up(up, down) {
  var temp_val = document.getElementById(up).textContent;
  document.getElementById(up).textContent = document.getElementById(down).textContent;
  document.getElementById(down).textContent = temp_val;
  document.getElementById(down).style.background = 'url(assets/day4/' + document.getElementById(down).textContent + '.jpg)';
  document.getElementById(up).style.background = 'url(assets/day4/' + document.getElementById(up).textContent + '.jpg)';
  increment_move();
}

function increment_move() {
  num_moves++;
  document.getElementById('moves').textContent = "Moves: " + num_moves;
}

function initialise(tab_position) {
  for (let i = 0; i < indexes.length; i++) {
    document.getElementById(indexes[i]).textContent = tab_position[i];
    document.getElementById(indexes[i]).style.background = 'url(assets/day4/' + tab_position[i] + '.jpg)';
  }
}

function show_image() {
  for (let i = 0; i < indexes.length; i++) {
    document.getElementById(indexes[i]).style.visibility = 'hidden';
    document.getElementById('tab_grid').style.background = 'url(assets/day4/complete.jpg)';
    setTimeout(back_to_normal, 5000);
  }
}

function back_to_normal() {
  document.getElementById('tab_grid').style.background = 'url(assets/day4/complete.jpg)';
  setTimeout(reset_images, 5000);
}

function reset_images() {
  for (let i = 0; i < indexes.length; i++) {
    document.getElementById(indexes[i]).style.visibility = 'visible';
    document.getElementById('launch').textContent = 'Start Game';
    num_moves = 0;
    document.getElementById('moves').textContent = 'Moves: 0';
  }
}

function is_solvable(arr) {
  puzzle = [[dict[arr[0]], dict[arr[1]], dict[arr[2]]],
            [dict[arr[3]], dict[arr[4]], dict[arr[5]]],
            [dict[arr[6]], dict[arr[7]], dict[arr[8]]]];
  console.log(puzzle);
  let inv_count = 0;
  for (let i = 0; i < puzzle.length; i++) {
    for (let j = i + 1; j < puzzle.length; j++) {
      if ((puzzle[i] && puzzle[j]) && puzzle[i] > puzzle[j]) inv_count++;
    }
  }
  return (inv_count % 2 == 0);
}
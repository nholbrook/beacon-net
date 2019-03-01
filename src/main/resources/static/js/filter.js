$(document).ready(function() {
    $('#filterbtnapply').click(function(){
      var filterLocation = [];
          $.each($("input[name='locationA']:checked"), function(){
              filterLocation.push($(this).val());
          });
      $('#filterdisplay1').show(function showFavorites() {
        $('#filterdisplay1').html("<strong>Point A:</strong> " + filterLocation.join(", "));
      });

      });

      $('#filterbtnapply').click(function(){
        var filterLocation = [];
            $.each($("input[name='locationB']:checked"), function(){
                filterLocation.push($(this).val());
            });
        $('#filterdisplay2').show(function showFavorites() {
          $('#filterdisplay2').html("<strong>Point B:</strong> " + filterLocation.join(", "));
        });

        });

      // Clear filter button
      $('#filterbtnclear').click(function() {
        $('#filterdisplay1').empty();
        $('#filterdisplay2').empty();
      });

  });

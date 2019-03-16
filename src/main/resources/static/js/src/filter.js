$(document).ready(function() {
    $('#filterbtnapply').click(function(){
      var filterLocation = [];
          $.each($("input[name='locationA']:checked"), function(){
              filterLocation.push($(this).val());
          });
      $('#filterdisplaytitle').show(function showFavorites() {
        $('#filterdisplaytitle').html("<strong>Filter:</strong> " + filterLocation.join(", "));
      });

      });

      // Clear filter button
      $('#filterbtnclear').click(function() {
        $('#filterdisplaytitle').empty();
        $('#filterdisplaytitle').html("<strong>Filter:</strong> None");
        $( ".filtercheckbox" ).prop( "checked", false );
      });

  });

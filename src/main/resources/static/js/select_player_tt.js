$(document).ready(function()
{
   $("#team1").change(function()
   {
   var teamId=$('#team1').val();
      getUserByTeamId(teamId,'player1');
   });
   $("#team2").change(function()
   {
   var teamId=$('#team2').val();
      getUserByTeamId(teamId,'player2');
   });

});

function getUserByTeamId(teamId,selectName)
{

   var json={};
   json.teamId=teamId;

   $.ajax({
      url:"/getTeamByTeamId?teamId="+teamId,
      type:"GET",
      success:function(res)
      {
      var jsonObj=res;
      $('#'+selectName).html('');
        for(var v=0;v<jsonObj.length;v++)
        {
           $('#'+selectName).append("<option>"+jsonObj[v].playerName+"</option>");
        }

      }

   });


}
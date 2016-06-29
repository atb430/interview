/**
 * Created by Austin on 6/25/2016.
 */
/*
* This function executes an AJAX call to the url of the mySidewalk API
* and then parses the JSON to get the text from the comment.  It is then 
* placed on a line in the html and formatted so that it is displayed in a 
* readable format.
*/
$(document).ready(function () {

    var $comment = $('#comment');

    $.ajax({
        type: 'GET',
        url: 'https://mysidewalk.com/api/engagement/v1/comments/100.json',
        dataType:'json',
        success: function(data){
            commentResponse = data.comments[0].text;
            console.log(commentResponse);
            $comment.append('<li>Comment: '+ commentResponse + '</li>');
        }

    });

});

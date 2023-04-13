$(function(){
    const appendTodo = function(data){
        var todoCode = '<input type="checkbox" id =' + data.id +' name = "isdone" ' + data.done + '>'
            + '<a href="#" class="todo-link" data-id="' +
            data.id + '">' + data.description + '</a>';
        $('#todo-list')
            .append('<div>' + todoCode + '</div>');
    };

    //Loading books on load page
   /*$.get('/todos/', function(response)
   {
       for(i in response) {
           appendTodo(response[i]);
       }
   });*/

    //Show adding book form
    $('#show-add-todo-form').click(function(){
        $('#todo-form').css('display', 'flex');
    });

    //Closing adding book form
    $('#todo-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Change isDone Status


    //Getting book
    $(document).on('click', '.todo-link', function(){
        var link = $(this);
        var todoId = link.data('id');
        $.ajax({
            method: "GET",
            url: '/todo/' + todoId,
            success: function(response)
            {
                var code = '<br><span>Выполнить до:' + response.date + '</span>';
                link.parent().append(code);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Дело не найдено!');
                }
            }
        });
        return false;
    });

    //delete todoItem

    //Adding todoItem
    $('#save-todo').click(function()
    {
        var data = $('#todo-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/todos/',
            data: data,
            success: function(response)
            {
                $('#todo-form').css('display', 'none');
                var todo = {};
                todo.id = response;
                var dataArray = $('#todo-form form').serializeArray();
                for(i in dataArray) {
                    todo[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTodo(todo);
            }
        });
        return false;
    });

});
$(document).ready(function(){
  dataToView(disks);
});

function dataToView(disks)
{
  divNums=0;
  $('#container').empty();
  var diskDivs=_.map(disks, function(disk){
    return diskDiv(disk);
  });

  _.each(diskDivs, function(div){
    $('#container').append(div);
  });

  $('.disk1').mouseenter(function(){
    $('#'+this.id+' > button').show();
  })
  .mouseleave(function(){
    $('#'+this.id+' > button').hide();
  });

  $('.disk1 > .delbtn').click(function(){
    var start=parseInt(this.id.charAt(this.id.length-1));
    disks.splice(start-1, 1);
    dataToView(disks);
  });

  $('.disk1 > .updbtn').click(function(){
    //console.log('update');
    //进去另一个页面
    var start=parseInt(this.id.charAt(this.id.length-1))-1;
    window.location.href="update.html?flag="+start;
  });
};

function diskDiv(disk)
{
  divNums++;
  var id="disk"+divNums;
  return $("<div>")
    .attr("class", 'disk1')
    .attr('id', id)
    .attr('style', 'margin-left: 20px')
    .append(diskTitle(disk.name))
    .append(diskImg(disk.img))
    .append(diskDesc(disk.desc))
    .append(diskDelBtn())
    .append(diskUpdBtn());
};

function diskTitle(title)
{
  return $('<h3>').html(title);
};

function diskImg(src)
{
  return $('<img>').attr('src', src);
};

function diskDesc(desc)
{
  return $('<p>').html(desc);
}

function diskDelBtn()
{
  var id='delbtn'+divNums;
  return $('<button>').html('delete')
    .attr('id', id)
    .attr('class', 'delbtn').hide();
}

function diskUpdBtn()
{
  var id='updbtn'+divNums;
  return $('<button>').html('update')
    .attr('id', id)
    .attr('class', 'updbtn').hide();
}

function add()
{
  var newProduct={name:$('#productName').val(),
  img:$('#productImg').val(),
  desc:$('#productDesc').val()};

  disks.push(newProduct);
  dataToView(disks);
}

function search()
{
  var keyword=$('#search').val();
  var searchResult=_.filter(disks, function(disk){
    return disk.name.indexOf(keyword) != -1;
  });
  dataToView(searchResult);
}

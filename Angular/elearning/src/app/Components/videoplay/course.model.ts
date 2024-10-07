export interface Course{
    coursename : String,
    courseduration :String,
    courseId : Number,
    coursedescription : String,
    thumbnail : String,
    videos : [ {
        id : Number,
        filename : String,
        fileURL : String,
    } ]
}

export interface Response {
    message : String
}


export interface videoStatus{
    videoId:Number,
    completed:boolean
}

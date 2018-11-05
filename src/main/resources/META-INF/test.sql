create table  student(
  student_id bigint not null primary key auto_increment
                     ,firstname varchar(60) not null
                     ,surname varchar (60) not null
                     ,patronymic varchar (60) not null
                     ,group_id bigint not null default 0
                     ,constraint fk_student_1 foreign key(group_id)
                       references group_ (group_id)
                      , unique(surname,firsname,patronymic)
   );

create table  student_to_lesson(
  student_id bigint not null default 0
  ,lesson_id bigint not null default  0
  , primary key(student_id,lesson_id)
  , constraint fk_student_to_lesson_1 foreign key(student_id)
  references student(student_id)
  , constraint fk_student_to_lesson_2 foreign key(lesson_id)
  references lesson(lesson_id)
   );

create table  group_(
  group_id bigint not null primary key auto_increment
                  ,name_group varchar(60)not null unique ,

  );
create table  subject(
  subject_id bigint not null primary key auto_increment
                    , subject_name varchar (60) not null unique
  );

create table  lesson(
lesson_id bigint not null primary key auto_increment
                   ,lesson_type ENUM('ELECTIVE', 'CIRCLES', 'CONSULTATION', 'SPORT_SECTION')  not null
                   ,date_ date
                   ,begin_lesson time
                   ,end_lesson time
                   ,teacher_id bigint not null default 0
                   , subject_id bigint not null default 0
                   ,constraint fk_lesson_1 foreign key (teacher_id)
                   references teacher(teacher_id)
                   , constraint fk_lesson_2 foreign key(subject_id)
                   references subject(subject_id)

  );
create table  teacher(
  teacher_id bigint not null primary key auto_increment
                   ,teacher_name varchar (60) not null
                   , teacher_surname varchar (60) not null
                   , teacher_patronymic varchar (60) not null
                   ,subject_id bigint not null default 0
                   , constraint fk_teacher_1 foreign key(subject_id)
                   references subject(subject_id),
                   unique (teacher_name,teacher_surname,teacher_patronymic)
  );
create table attendance(
  attend_id bigint not null primary key auto_increment
  ,is_present tinyint(1) default null
 ,date_ date
);


create table attend_lesson_student(
attend_id bigint not null default 0
,lesson_id bigint not null default  0
   ,student_id bigint not null default 0
   , constraint fk_attendance_1 foreign key (attend_id)
   references attendance(attend_id)
   , constraint  fk_attendance_2 foreign key (student_id)
   references student(student_id)
   ,constraint  fk_attendance_3 foreign key (lesson_id)
     references lesson(lesson_id)

);

create table  student_to_subject(
  student_id bigint not null default 0
  ,subject_id bigint not null default  0
  , primary key(student_id,subject_id)
  , constraint fk_student_to_subject_1 foreign key(student_id)
  references student(student_id)
  , constraint fk_student_to_subject_2 foreign key(subject_id)
  references subject(subject_id)
  );

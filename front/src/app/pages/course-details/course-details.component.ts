import { Component, inject } from '@angular/core';

// service
import { TecflixApiService } from '../../service/tecflix-api/tecflix-api.service';

// router
import { ActivatedRoute, Router } from '@angular/router';

// components
import { HeaderVersionOneComponent } from '../../shared/header-version-one/header-version-one.component';
import { FooterComponent } from '../../shared/footer/footer.component';
import { CommonModule, CurrencyPipe} from '@angular/common';

// rxjs
import { forkJoin } from 'rxjs';

// interfaces
import { Course } from '../../model/course';
import { Rating } from '../../model/rating';
import { Professor } from '../../model/professor';
import { User } from '../../model/user';
import { Category } from '../../model/category';
import { CourseCategories } from '../../model/course-categories';
import { Module } from '../../model/module';
import { Class } from '../../model/class';

interface CourseData {
  course: Course;
  content: {module: Module, classes: Class[]}[];
  categories: Category[];
  ratings: Rating[];
  professor: Professor;
  user: User;
}

@Component({
  selector: 'app-course-details',
  standalone: true,
  imports: [HeaderVersionOneComponent, FooterComponent, CurrencyPipe, CommonModule],
  templateUrl: './course-details.component.html',
  styleUrls: ['./course-details.component.scss']
})

export class CourseDetailsComponent {
  #tecflixApiService = inject(TecflixApiService);
  #route = inject(ActivatedRoute);
  #router = inject(Router);
  
  private urlParamsId: string | null = '';

  private course: Course[] = [];
  private courseModules: Module[] = [];
  public courseClasses: Class[] = [];

  private categories: Category[] = [];
  private courseCategories: CourseCategories[] = [];
  private allProfessors: Professor[] = [];
  private allRatings: Rating[] = [];
  private allUsers: User[] = [];


  public allData: CourseData | null = null;

  public displayAllClasses: boolean = false;
  public displayClasses: string[] = [];

  constructor() {}

  ngOnInit(): void {
    this.loadData();
  }

  private loadData(): void {
    this.#route.paramMap.subscribe(params => this.urlParamsId = params.get('id'))
    
    if (!this.urlParamsId) return;
    
    forkJoin({
      course: this.#tecflixApiService.getCourse(this.urlParamsId),
      modules: this.#tecflixApiService.getModulesByCourseId(this.urlParamsId),
      classes: this.#tecflixApiService.getClassesByCourseId(this.urlParamsId),
      ratings: this.#tecflixApiService.getAllRatings(),
      courseCategories: this.#tecflixApiService.getCourseCategories(),
      professors: this.#tecflixApiService.getAllProfessors(),
      users: this.#tecflixApiService.getAllUsers(),
    }).subscribe({
      next: ({course, modules, classes, courseCategories, ratings, professors, users}) => {
        this.course = course;
        this.courseModules = modules;
        this.courseClasses = classes;
        this.courseCategories = courseCategories;
        this.allRatings = ratings;
        this.allProfessors = professors;
        this.allUsers = users;
        
        this.getCourseCategories();
        this.mapData();
      },
      error: error => console.error(error)
    })
  }

  private getCourseRatings(): Rating[] {
    return this.allRatings.filter(rating => rating.course_id == this.course[0].id);
  }

  private getCourseCategories(): void {
    const courseCategories: CourseCategories[] = 
      this.courseCategories.filter(element => element.course_id == this.course[0].id);
    for (let category of courseCategories) {
      this.getCategoryById(category.category_id);
    }
  }

  private getCategoryById(id: string): void {
    this.#tecflixApiService.getCategoryById(id).subscribe({
      next: value => this.categories.push(value[0]),
      error: error => console.error(error)
    })
  }

  private getCourseProfessor(): Professor {
    return this.allProfessors.filter(professor => professor.id == this.course[0].professor_id)[0];
  }

  private getCourseProfessorUser(): User {
    return this.allUsers.filter(user => user.id == this.getCourseProfessor().user_id)[0];
  }


  private getModuleClasses(moduleId: string): Class[] {
    return this.courseClasses.filter(element => element.module_id == moduleId);
  }

  private getContentCourse(): {module: Module, classes: Class[]}[] {
    const content: {module: Module, classes: Class[]}[] = [];
    for (let module of this.courseModules) {
      content.push({module: module, classes: this.getModuleClasses(module.id)})
    }
    return content;
  }

  private mapData() {
    if (!this.course) return;

    this.allData = {
      course: this.course[0],
      content: this.getContentCourse(),
      categories: this.categories,
      ratings: this.getCourseRatings(),
      professor: this.getCourseProfessor(),
      user: this.getCourseProfessorUser(),
    }
  }

  public averageRating(ratings: Rating[] | undefined): number {
    if (!ratings) return 0;

    let sumRating: number = 0;
    for (let rating of ratings) {
      sumRating += rating.grade;
    }

    return sumRating / ratings.length;
  }

  public toggleAllClassesVisibility() {
    this.displayAllClasses = !this.displayAllClasses;
  }

  public toggleClassesVisibility(id: string) {
    // const hasTheId: string | undefined = this.displayClasses.find(value => value === id); 
    
    if (this.hasTheId(id)) {
      this.displayClasses = this.displayClasses.filter(value => value !== id)
    } else {
      this.displayClasses.push(id);
    }
  }

  public hasTheId(moduleId: string): boolean {
    const index = this.displayClasses.indexOf(moduleId);
    if (index != -1) return true;
    return false;
  }

  public navigate(path: string) {
    this.#router.navigate([path])
  }
}

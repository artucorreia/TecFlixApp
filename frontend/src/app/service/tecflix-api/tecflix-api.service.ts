import { Injectable, inject } from '@angular/core';
import { User } from '../../model/user';
import { Rating } from '../../model/rating';
import { Class } from '../../model/class';
import { Module } from '../../model/module';
import { Course } from '../../model/course';
import { UserCourse } from '../../model/user-course';
import { HttpClient } from '@angular/common/http';
import { Observable, forkJoin, map, mergeMap } from 'rxjs';
import { Professor } from '../../model/professor';
import { Category } from '../../model/category';
import { CourseCategories } from '../../model/course-categories';

@Injectable({
  providedIn: 'root'
})
export class TecflixApiService {
  #http = inject(HttpClient);

  private url: string = "http://localhost:3000/";

  public getUserById(userId: string): Observable<User> {
    const param = {id: userId};
    return this.#http.get<User>(`${this.url}users`, {params: param});
  }

  public getAllUsers(): Observable<User[]> {
    return this.#http.get<User[]>(`${this.url}users`);
  }

  public getProfessorById(professorId: string): Observable<Professor> {
    const params = {id: professorId};
    return this.#http.get<Professor>(`${this.url}professors`, {params: params});
  }

  public getAllProfessors(): Observable<Professor[]> {
    return this.#http.get<Professor[]>(`${this.url}professors`);
  }

  public getRatings(courseId: string): Observable<Rating[]> {
    const params = {course_id: courseId};
    return this.#http.get<Rating[]>(`${this.url}ratings`, {params: params});
  }

  public getAllRatings(): Observable<Rating[]> {
    return this.#http.get<Rating[]>(`${this.url}ratings`);
  }

  public getClassesByModuleId(moduleId: string): Observable<Class[]> {
    const params = {module_id: moduleId};
    return this.#http.get<Class[]>(`${this.url}classes`, {params: params});
  }

  public getClassesByCourseId(courseId: string): Observable<Class[]> {
    return this.getModulesByCourseId(courseId).pipe(
      mergeMap((modules: Module[]) => {
        
        const classObservables = modules.map(module => this.getClassesByModuleId(module.id));
        
        return forkJoin(classObservables).pipe(
          map((classesArray: Class[][]) => classesArray.flat())
        );

      })
    );
  }

  public getModulesByCourseId(couseId: string): Observable<Module[]> {
    const params = {course_id: couseId}
    return this.#http.get<Module[]>(`${this.url}modules`, {params: params});
  }

  public getAllCourses(): Observable<Course[]> {
    return this.#http.get<Course[]>(`${this.url}courses`);
  }

  public getCourse(courseId: string): Observable<Course[]> {
    const params = {id: courseId}
    return this.#http.get<Course[]>(`${this.url}courses`, {params: params});
  }

  public getAllCategories(): Observable<Category[]> {
    return this.#http.get<Category[]>(`${this.url}categories`);
  }

  public getCategoryById(id: string): Observable<Category[]> {
    const params = {id: id}
    return this.#http.get<Category[]>(`${this.url}categories`, {params: params});
  }

  public getCourseCategories(): Observable<CourseCategories[]> {
    return this.#http.get<CourseCategories[]>(`${this.url}course_categories`);
  }

  // public getUserCouses(userId: string): UserCourse[] {
  //   const userCourses: UserCourse[] = [
  //     { userId: '101', cursoId: '1', dataCompra: new Date('2023-01-01') },
  //     { userId: '101', cursoId: '2', dataCompra: new Date('2023-02-01') },
  //     { userId: '102', cursoId: '3', dataCompra: new Date('2023-03-01') },
  //     { userId: '103', cursoId: '4', dataCompra: new Date('2023-04-01') },
  //     { userId: '104', cursoId: '5', dataCompra: new Date('2023-05-01') },
  //     { userId: '104', cursoId: '1', dataCompra: new Date('2023-06-01') }
  //   ];
  //   return userCourses.filter(value => value.userId == userId);
  // }
}

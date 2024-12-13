import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private url: string = 'http://localhost:3000/';

  constructor(private http: HttpClient) { }

  public user(): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}users`);
  }

  public userByEmail(email: string): Observable<User[]> {
    const params = {email: email};
    return this.http.get<User[]>(`${this.url}users`, {params: params});
  }

  public singUp(fullName: string, email: string, password: string): Observable<User> {
    return this.http.post<User>(`${this.url}users`, {
      fullName: fullName,
      email: email,
      password: this.hashCode(password),
      ativo: true
    });
  }

  public hashCode(string: string): string {
    var hash = 0, i, chr;
    
    if (string.length === 0) return hash.toString();
    
    for (i = 0; i < string.length; i++) {
      chr = string.charCodeAt(i);
      hash = ((hash << 5) - hash) + chr;
      hash |= 0;
    }

    return hash.toString();
  }
}

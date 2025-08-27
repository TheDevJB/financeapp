import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterModule],
  template: `
    <div class="container">
      <nav class="navbar">
        <div class="nav-brand">
          <h2>FinanceApp</h2>
        </div>
        <div class="nav-links">
          <a routerLink="/login">Login</a>
          <a routerLink="/signup" class="btn-outline">Sign Up</a>
        </div>
      </nav>

      <section class="hero">
        <div class="hero-content">
          <h1>Outsmart Money, Alone or Together</h1>
          <p class="hero-subtitle">FinanceApp is the simplest way for individuals and couples to manage money.</p>
          <a routerLink="/signup" class="btn-primary">Get Started</a>
        </div>
      </section>

      <section class="features">
        <div class="feature-content">
          <h2>Get On The Same Page</h2>
          <p>Argue less, and save more with your soulmate or manage solo. Choose what you want to share.</p>
        </div>
      </section>


    </div>
  `,
  styles: [`
    .container {
      min-height: 100vh;
      background: linear-gradient(135deg, #1a1a1a 0%, #2d2d2d 100%);
    }

    .navbar {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1rem 2rem;
      background: rgba(0, 0, 0, 0.1);
      backdrop-filter: blur(10px);
    }

    .nav-brand h2 {
      color: #ffffff;
      font-weight: 600;
      font-size: 1.5rem;
    }

    .nav-links {
      display: flex;
      gap: 1rem;
      align-items: center;
    }

    .nav-links a {
      color: #e0e0e0;
      text-decoration: none;
      padding: 0.5rem 1rem;
      border-radius: 6px;
      transition: all 0.3s ease;
    }

    .nav-links a:hover {
      color: #ffffff;
      background: rgba(255, 255, 255, 0.1);
    }

    .btn-outline {
      border: 1px solid #4ade80 !important;
      color: #4ade80 !important;
    }

    .btn-outline:hover {
      background: #4ade80 !important;
      color: #000000 !important;
    }

    .hero {
      text-align: center;
      padding: 6rem 2rem;
      background: linear-gradient(135deg, #1a1a1a 0%, #2d2d2d 50%, #1a1a1a 100%);
    }

    .hero-content h1 {
      font-size: 4rem;
      font-weight: 700;
      color: #ffffff;
      margin-bottom: 1.5rem;
      line-height: 1.2;
    }

    .hero-subtitle {
      font-size: 1.25rem;
      color: #b0b0b0;
      margin-bottom: 3rem;
      max-width: 600px;
      margin-left: auto;
      margin-right: auto;
    }

    .btn-primary {
      background: linear-gradient(135deg, #4ade80 0%, #22c55e 100%);
      color: #000000;
      padding: 1rem 2rem;
      border-radius: 8px;
      text-decoration: none;
      font-weight: 600;
      font-size: 1.1rem;
      transition: all 0.3s ease;
      display: inline-block;
      box-shadow: 0 4px 15px rgba(74, 222, 128, 0.3);
    }

    .btn-primary:hover {
      transform: translateY(-2px);
      box-shadow: 0 8px 25px rgba(74, 222, 128, 0.4);
    }

    .features {
      padding: 4rem 2rem;
      background: #0a0a0a;
      text-align: center;
    }

    .feature-content h2 {
      font-size: 2.5rem;
      color: #ffffff;
      margin-bottom: 1rem;
    }

    .feature-content p {
      font-size: 1.1rem;
      color: #b0b0b0;
      max-width: 600px;
      margin: 0 auto;
    }



    @media (max-width: 768px) {
      .hero-content h1 {
        font-size: 2.5rem;
      }
      
      .hero {
        padding: 4rem 1rem;
      }
      
      .navbar {
        padding: 1rem;
      }
    }
  `]
})
export class HomeComponent {}

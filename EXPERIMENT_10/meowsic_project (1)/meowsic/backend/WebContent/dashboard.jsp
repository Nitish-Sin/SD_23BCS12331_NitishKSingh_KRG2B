<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="meowsic.model.User" %>
<%
    User user = (User) session.getAttribute("loggedInUser");
    if (user == null) {
        response.sendRedirect("index.html?error=session_expired");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Meowsic — Dashboard</title>
  <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&family=Nunito:wght@400;600;700;800&display=swap" rel="stylesheet"/>
  <style>
    *, *::before, *::after { box-sizing: border-box; margin:0; padding:0; }
    :root {
      --bg: #0d0d14; --card: #1e1e2e; --surface: #16161f;
      --accent: #ff6b9d; --accent2: #c084fc;
      --text: #f0e6ff; --muted: #8b7fa8;
      --border: rgba(255,107,157,0.18);
    }
    body {
      font-family: 'Nunito', sans-serif;
      background: var(--bg); color: var(--text);
      min-height: 100vh; display: flex; flex-direction: column;
      align-items: center; justify-content: center; gap: 1.5rem;
      background-image:
        radial-gradient(ellipse 60% 50% at 20% 30%, rgba(192,132,252,0.10) 0%, transparent 70%),
        radial-gradient(ellipse 50% 60% at 80% 70%, rgba(255,107,157,0.08) 0%, transparent 70%);
    }
    .card {
      background: var(--card); border: 1px solid var(--border);
      border-radius: 24px; padding: 2.5rem 3rem; text-align: center;
      max-width: 480px; width: 90%;
      box-shadow: 0 0 30px rgba(255,107,157,0.2), 0 25px 60px rgba(0,0,0,0.5);
      animation: in 0.5s cubic-bezier(0.34,1.56,0.64,1) both;
    }
    @keyframes in { from { opacity:0; transform: translateY(30px) scale(0.95); } to { opacity:1; transform:none; } }
    .avatar { font-size: 4rem; margin-bottom: 0.5rem; }
    .brand { font-family: 'Fredoka One', cursive; font-size: 1.4rem;
      background: linear-gradient(135deg, #ff6b9d, #c084fc);
      -webkit-background-clip: text; -webkit-text-fill-color: transparent; background-clip: text;
    }
    .welcome { font-family: 'Fredoka One', cursive; font-size: 2rem; color: var(--accent); margin-top: 0.5rem; }
    .username { font-size: 1rem; color: var(--muted); font-weight: 700; margin-top: 0.2rem; }
    .msg { font-size: 0.95rem; opacity: 0.8; margin-top: 1rem; }
    .now-playing {
      display: flex; align-items: center; gap: 1rem;
      background: var(--surface); border-radius: 12px; padding: 0.85rem 1.2rem;
      margin-top: 1.5rem; border: 1px solid var(--border);
    }
    .np-bar { display: flex; gap: 3px; align-items: flex-end; }
    .np-bar span { display: block; width: 4px; background: var(--accent); border-radius: 2px;
      animation: bounce 0.8s ease-in-out infinite alternate; }
    .np-bar span:nth-child(1){height:14px;animation-delay:0s}
    .np-bar span:nth-child(2){height:22px;animation-delay:.15s}
    .np-bar span:nth-child(3){height:10px;animation-delay:.3s}
    .np-bar span:nth-child(4){height:18px;animation-delay:.1s}
    .np-bar span:nth-child(5){height:12px;animation-delay:.25s}
    @keyframes bounce { from{transform:scaleY(0.4)} to{transform:scaleY(1)} }
    .np-info strong { display: block; font-weight: 800; font-size: 0.95rem; }
    .np-info span   { font-size: 0.8rem; color: var(--muted); font-weight: 600; }
    .logout {
      display: inline-block; margin-top: 1.5rem;
      padding: 0.65rem 2rem;
      border: 1.5px solid var(--border); border-radius: 10px;
      color: var(--muted); font-family: 'Nunito', sans-serif;
      font-weight: 800; font-size: 0.9rem; text-decoration: none;
      transition: all 0.2s;
    }
    .logout:hover { border-color: var(--accent); color: var(--accent); }
  </style>
</head>
<body>
  <div class="card">
    <div class="avatar">😸</div>
    <div class="brand">🎵 Meowsic</div>
    <div class="welcome">Purrfect!</div>
    <div class="username">@<%= user.getUserId() %></div>
    <% if (user.getDisplayName() != null && !user.getDisplayName().isEmpty()) { %>
      <div style="margin-top:0.3rem;font-weight:700;font-size:1.05rem"><%= user.getDisplayName() %></div>
    <% } %>
    <div class="msg">You're in the groove. Let the music purr. 🎶</div>
    <div class="now-playing">
      <div class="np-bar">
        <span></span><span></span><span></span><span></span><span></span>
      </div>
      <div class="np-info">
        <strong>Moonlight Meowody</strong>
        <span>Whisker &amp; The Claws · Nap Time Lofi</span>
      </div>
    </div>
    <a class="logout" href="LogoutServlet">← Sign Out</a>
  </div>
</body>
</html>

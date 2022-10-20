package malalife.FishFesta;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class FishFesta_Timer implements Runnable
{
	public static final long Running_Interval = 20;
	public static FishFesta_Timer Instance;
	
	FishFesta_Main main;
	long max_remained_time = 0;
	long counted_time = 0;
	
	HashMap<String, String> Timer = new HashMap<String, String>();
	
	
	public FishFesta_Timer()
	{
		main = FishFesta_Main.Instance;
		Instance = this;
		Initialize(0);
	}
	
	public void Initialize(long _setted_time)
	{
		Timer.clear();
		counted_time = 0;
		max_remained_time = _setted_time;
	}

	public void run()
	{
		// 플레이중이 아니라면 걍 리턴
		if(!main.m_Now_Playing)
			return;
		
		counted_time += 1;
		if (counted_time % 300 == 0)
		{
			String msg = FF_Msg_TBL.Get_Msg("Notice_Festa_Now_Playing", true);
			Bukkit.broadcastMessage(msg);
		}
		
		main.m_Remained_Ticks -= Running_Interval;
		Timer_Alarm();
		
		main.bar.setTitle(FF_Msg_TBL.Get_Msg("TimerBar_Title", false) + " " + Make_TimeMsg());
		main.bar.setProgress((double)main.m_Remained_Ticks / (double)max_remained_time);
		if (main.m_Remained_Ticks <= 0)
			main.End_Festa(null, false, new String[1]);
	}
	
	String Make_TimeMsg()
	{
		int h = (int)(main.m_Remained_Ticks / 20 / 60 / 60);
		int m = (int)(main.m_Remained_Ticks - (h * 60 * 60 * 20)) / 20 / 60;
		int s = (int)(main.m_Remained_Ticks - (h * 60 * 60 * 20) - (m * 60 * 20)) / 20;
		
		return "§f§l[" + h + FF_Msg_TBL.Get_Msg("Hour", false) + " "
				+ m + FF_Msg_TBL.Get_Msg("Minute", false) + " "
				+ s + FF_Msg_TBL.Get_Msg("Second", false) + "]";
	}
	
	void Timer_Alarm()
	{
		// 한 시간 남았을 때
		if(main.m_Remained_Ticks / 20 / 60 / 60 < 1 && max_remained_time / 20 / 60 / 60 >= 1)
		{
			if(Timer.get("1_hour") == null)
			{
				Timer.put("1_hour", "true");
				// 방송 조지기
				Broadcast_Remained_Time("1" + FF_Msg_TBL.Get_Msg("Hour", false));
			}
		}
		// 30분 남았을 때
		if(main.m_Remained_Ticks / 20 / 60 < 30 && max_remained_time / 20 / 60 >= 30)
		{
			if(Timer.get("30_minutes") == null)
			{
				Timer.put("30_minutes", "true");
				// 방송 조지기
				Broadcast_Remained_Time("30" + FF_Msg_TBL.Get_Msg("Minute", false));
			}
		}
		// 15분 남았을 때
		if(main.m_Remained_Ticks / 20 / 60 < 15 && max_remained_time / 20 / 60 >= 15)
		{
			if(Timer.get("15_minutes") == null)
			{
				Timer.put("15_minutes", "true");
				// 방송 조지기
				Broadcast_Remained_Time("15" + FF_Msg_TBL.Get_Msg("Minute", false));
			}
		}
		// 10분 남았을 때
		if(main.m_Remained_Ticks / 20 / 60 < 10 && max_remained_time / 20 / 60 >= 10)
		{
			if(Timer.get("10_minutes") == null)
			{
				Timer.put("10_minutes", "true");
				// 방송 조지기
				Broadcast_Remained_Time("10" + FF_Msg_TBL.Get_Msg("Minute", false));
			}
		}
		// 5분 남았을 때
		if(main.m_Remained_Ticks / 20 / 60 < 5 && max_remained_time / 20 / 60 >= 5)
		{
			if(Timer.get("5_minutes") == null)
			{
				Timer.put("5_minutes", "true");
				// 방송 조지기
				Broadcast_Remained_Time("5" + FF_Msg_TBL.Get_Msg("Minute", false));
			}
		}
		// 3분 남았을 때
		if(main.m_Remained_Ticks / 20 / 60 < 3 && max_remained_time / 20 / 60 >= 3)
		{
			if(Timer.get("3_minutes") == null)
			{
				Timer.put("3_minutes", "true");
				// 방송 조지기
				Broadcast_Remained_Time("3" + FF_Msg_TBL.Get_Msg("Minute", false));
			}
		}
		// 1분 남았을 때
		if(main.m_Remained_Ticks / 20 / 60 < 1 && max_remained_time / 20 / 60 >= 1)
		{
			if(Timer.get("1_minute") == null)
			{
				Timer.put("1_minute", "true");
				// 방송 조지기
				Broadcast_Remained_Time("1" + FF_Msg_TBL.Get_Msg("Minute", false));
			}
		}
		// 10초 카운터
		for(int i = 1; i <= 10; i++)
		{
			if(main.m_Remained_Ticks / 20 <= i)
			{
				if(Timer.get(i + "_seconds") == null)
				{
					Timer.put(i + "_seconds", "true");
					// 방송 조지기
					Broadcast_Remained_Time(i + FF_Msg_TBL.Get_Msg("Second", false));
				}
			}
		}
	}
	void Broadcast_Remained_Time(String _time)
	{
		String msg = FF_Msg_TBL.Get_Msg("Notice_Festa_Time_Alarm", false);
		msg = msg.replace("{time}", _time);

		for(Player p : main.m_Players)
		{
			p.sendMessage(msg);
		}
	}
}

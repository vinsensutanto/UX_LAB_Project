package com.example.labux.ui.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.labux.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentCarousel extends Fragment {

    private ViewPager2 viewPager, viewPager2;
    private CarouselAdapter adapter, adapter2;
    private Handler handler = new Handler();
    private Runnable runnable;
    private List<CarouselItem> carouselItems, carouselItems2;

    // 🔹 Tambahan (supaya bisa pilih data beda tiap instance)
    private static final String ARG_MODE = "mode";

    public static FragmentCarousel newInstance(String mode) {
        FragmentCarousel fragment = new FragmentCarousel();
        Bundle args = new Bundle();
        args.putString(ARG_MODE, mode);
        fragment.setArguments(args);
        return fragment;
    }
    // 🔹 End tambahan

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentcarousel, container, false);

        viewPager = view.findViewById(R.id.viewPagerCarousel);
        viewPager2 = view.findViewById(R.id.viewPagerCarousel2);

        // Data carousel SEASONAL ANIME
        carouselItems = new ArrayList<>();
        carouselItems.add(new CarouselItem(R.drawable.kaijuu, "Kaijuu No.8", "Kaiju No. 8 Season 2 Confirms Release Date With A Brand New Key Visual", "Kaiju No. 8 Season 2 gets its much-awaited release date along with a new promotional key visual. The second season will premiere on July 19 at 11:00 pm JST (7:30 pm IST) on TV Tokyo and affiliated channels. It will stream on Crunchyroll, as did the first season."));
        carouselItems.add(new CarouselItem(R.drawable.tatenoyuusha, "Tate no Yuusha", "Tate no Yuusha: Confirmed Season 4 for July 2025", "As some of you may remember, the official media of the anime Tate no Yuusha no Nariagari (The Rising of the Shield Hero) announced that this February 25, new information about the expected fourth season of the series would be released in a live broadcast with the participation of voice actresses Asami Seto (voice of Raphtalia) and Rina Hidaka (voice of Filo)"));
        carouselItems.add(new CarouselItem(R.drawable.windbreaker, "Wind Breaker Season 2", "Wind Breaker season 2 episode 7: Release date and time", "Wind Breaker Season 2 Episode 7 will be released on Friday, May 16, 2025, at 12:26 am JST. Due to time zone differences, most international viewers will see it during the day on May 15. "));
        carouselItems.add(new CarouselItem(R.drawable.ruruonikenshin, "Rurouni Kenshin Season 3", "Rurouni Kenshin season 3 confirmed to be in production", "Liden Films, the studio responsible for the previous two seasons, is expected to continue with the third installment. The studio has been praised for its consistent animation quality and faithfulness to the original story. If Liden Films returns, fans can expect the same high production standards."));
        carouselItems.add(new CarouselItem(R.drawable.bokunohero, "Boku no Hero Academia", "My Hero Academia season 8 release date speculation and story", "My Hero Academia's final season (AKA My Hero Academia season 8) is officially hitting our screens this October."));

        // Data carousel ANIME MOVIE
        carouselItems2 = new ArrayList<>();
        carouselItems2.add(new CarouselItem(R.drawable.jjk, "Jujutsu Kaisen Movie", "Jujutsu Kaisen’s Return to Theaters Nets Impressive Box Office Debut", "Obviously, the movie did not do nearly as well as the previous theatrical release from the franchise, which hit $23.5 M in the opening weekend and went on to rake up $185 M worldwide. This is because this film is a rerelease of the already-released Hidden Inventory arc that aired in 2023, and went on to be massively popular and successful."));
        carouselItems2.add(new CarouselItem(R.drawable.kny, "Kimetsu no Yaiba Movie", "Demon Slayer: Kimetsu no Yaiba Infinity Castle International Release Dates Announced!", "Crunchyroll, the ultimate home for anime worldwide, announced today the North American and international theatrical dates for the highly anticipated Demon Slayer: Kimetsu no Yaiba Infinity Castle. The first film in the epic trilogy from famed animation studio ufotable will come exclusively to theatres on September 12, 2025 in United States and Canada, including in IMAX(R) and other premium large formats."));
        carouselItems2.add(new CarouselItem(R.drawable.dc, "Detective Conan", "Detective Conan’s 28th Film Gets a 2025 Release Date", "On Wednesday, the official website for Detective Conan (also known as Case Closed) exciting news about the franchise’s upcoming 28th film. Titled exciting news about the franchise’s upcoming 28th film. Titled *Meitantei Conan: Sekigan nMeitantei Conan: Sekigan no Flashback (Detective Conan: One-Eyed Flashback), the movie is set to premiere in Japan on April 18, 2025. Along with this announcement, the site revealed a new visual, illustrated by Detective Conan creator Gōshō Aoyama, generating buzz among fans."));

        // 🔹 Cek argumen mode → kalau dipanggil pakai newInstance("NEWS") / newInstance("MOVIE")
        String mode = getArguments() != null ? getArguments().getString(ARG_MODE) : null;

        if ("NEWS".equals(mode)) {
            adapter = new CarouselAdapter(requireContext(), carouselItems);
            viewPager.setAdapter(adapter);
            viewPager2.setVisibility(View.GONE); // sembunyiin viewPager2
        } else if ("MOVIE".equals(mode)) {
            adapter2 = new CarouselAdapter(requireContext(), carouselItems2);
            viewPager2.setAdapter(adapter2);
            viewPager.setVisibility(View.GONE); // sembunyiin viewPager
        } else {
            adapter = new CarouselAdapter(requireContext(), carouselItems);
            viewPager.setAdapter(adapter);

            adapter2 = new CarouselAdapter(requireContext(), carouselItems2);
            viewPager2.setAdapter(adapter2);
        }

        // Auto-scroll
        runnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager.getVisibility() == View.VISIBLE && carouselItems.size() > 0) {
                    int currentItem = viewPager.getCurrentItem();
                    int nextItem = (currentItem + 1) % carouselItems.size();
                    viewPager.setCurrentItem(nextItem, true);
                }
                if (viewPager2.getVisibility() == View.VISIBLE && carouselItems2.size() > 0) {
                    int currentItem2 = viewPager2.getCurrentItem();
                    int nextItem2 = (currentItem2 + 1) % carouselItems2.size();
                    viewPager2.setCurrentItem(nextItem2, true);
                }
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(runnable, 3000);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
    }
}

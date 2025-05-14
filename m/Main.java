package m;

import C.User;
import C_ITEMS.*;
import Screens.*;

import java.awt.Font;

public class Main {
    public static final String mainScreenPath = "graphs/gptMainScreen3.png";
    public static final int AMOUNT_OF_CHARACTER_PHOTOS = 9;
    public static User theUser;
    private static Font font;
    public static MusicPlayer musicPlayer = new MusicPlayer();
    public static final String MUSIC_PATH = "m/music.wav";

    public static void main(String[] args) {
        fillItemIcons();
        theUser = new User();
        MainFrame frame = new MainFrame();
        frame.getFirstScreen();
        musicPlayer.playMusic(MUSIC_PATH);
    }
    public static void checkIfMusicShouldPlay()
    {
        if (MainFrame.currentPanel instanceof FirstMenu) {
        if (SettingsScreen.musicInFirstScreen) {
            if (MusicPlayer.currentlyClosed) musicPlayer.resumeMusic();
        } else {
            musicPlayer.pauseMusic();
        }
    } else if (MainFrame.currentPanel instanceof LobbyScreen) {
        if (SettingsScreen.musicInLobby) {
            if (MusicPlayer.currentlyClosed) musicPlayer.resumeMusic();
        } else {
            musicPlayer.pauseMusic();
        }
    } else if (MainFrame.currentPanel instanceof FightScreen) {
        if (SettingsScreen.musicInFight) {
            if (MusicPlayer.currentlyClosed) musicPlayer.resumeMusic();
        } else {
            musicPlayer.pauseMusic();
        }
    } else if (MainFrame.currentPanel instanceof StoreScreen) {
        if (SettingsScreen.musicInStore) {
            if (MusicPlayer.currentlyClosed) musicPlayer.resumeMusic();
        } else {
            musicPlayer.pauseMusic();
        }
    } else if (MainFrame.currentPanel instanceof StoryScreen) {
        if (SettingsScreen.musicInStory) {
            if (MusicPlayer.currentlyClosed) musicPlayer.resumeMusic();
        } else {
            musicPlayer.pauseMusic();
        }
    }

    }
    public static Font getFont()
    {
        return font;
    }
    public static void setFont(Font f)
    {
        font = f;
    }

    public static void fillItemIcons()
    {
        Shield.fillIcons();
        Armor.fillIcons();
        Helmet.fillIcons();
        Sword.fillIcons();
        Leggings.fillIcons();
        Cat.fillIcons();
    }
}
